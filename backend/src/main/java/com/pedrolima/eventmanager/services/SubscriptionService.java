package com.pedrolima.eventmanager.services;

import com.pedrolima.eventmanager.dto.SubscriptionDTO;
import com.pedrolima.eventmanager.dto.UserSubscriptionsDTO;
import com.pedrolima.eventmanager.entities.Event;
import com.pedrolima.eventmanager.entities.Subscription;
import com.pedrolima.eventmanager.entities.User;
import com.pedrolima.eventmanager.entities.enums.SubscriptionStatus;
import com.pedrolima.eventmanager.exceptions.BusinessException;
import com.pedrolima.eventmanager.exceptions.ResourceNotFoundException;
import com.pedrolima.eventmanager.mapper.SubscriptionMapper;
import com.pedrolima.eventmanager.mapper.UserSubscriptionMapper;
import com.pedrolima.eventmanager.repositories.SubscriptionRepository;
import com.pedrolima.eventmanager.repositories.UserRepository;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final UserRepository userRepository;
  private final EventService eventService;
  private final SubscriptionMapper mapper;
  private final UserSubscriptionMapper userSubscriptionMapper;

  @Transactional(readOnly = true)
  public Page<Subscription> findAll(Pageable pageable) {
    return subscriptionRepository.findAll(pageable);
  }

  public Subscription findById(Long id) {
    return subscriptionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Subscription not found for id: " + id));
  }

  public Subscription insert(SubscriptionDTO objDTO, SubscriptionStatus status) {

    validateNewSubscription(objDTO.getUserId(), objDTO.getEventId());

    Event event = eventService.findById(objDTO.getEventId());
    eventService.checkBeginDateTime(event);
    eventService.decreaseVacancies(event);

    Subscription subscription = mapper.toEntity(objDTO);
    subscription.setEvent(event);
    User user = userRepository.findById(objDTO.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    subscription.setUser(user);
    subscription.setMoment(Instant.now());
    subscription.setStatus(status);

    return subscriptionRepository.save(subscription);
  }

  public Subscription cancelSubscription(Long id) {
    Subscription sub = findById(id);
    if (sub.isCheckedIn()) {
      throw new BusinessException(
          "Unable to cancel subscription! User has already checked in to the Event.");
    }
    if (sub.getStatus() == SubscriptionStatus.CANCELED) {
      throw new BusinessException("Subscription already canceled");
    }

    sub.getEvent().setVacancies(sub.getEvent().getVacancies() + 1);
    sub.setStatus(SubscriptionStatus.CANCELED);
    return subscriptionRepository.save(sub);
  }

  public Subscription checkIn(Long id) {
    Subscription sub = subscriptionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Subscription not found for id: " + id));

    if (!isCheckinOpen(sub.getEvent())) {
      throw new BusinessException("Check-in is closed.");
    }
    if (sub.getStatus() != SubscriptionStatus.CONFIRMED) {
      throw new BusinessException("Subscription not confirmed");
    }
    if (sub.isCheckedIn()) {
      throw new BusinessException("Subscription has already checked in.");
    }

    sub.setCheckedIn(true);
    return subscriptionRepository.save(sub);

  }

  private boolean isCheckinOpen(Event event) {
    Instant checkInBeginTime = event.getBeginDateTime().toInstant(ZoneOffset.UTC)
        .minus(1, ChronoUnit.HOURS);
    Instant checkInEndTime = event.getEndDateTime().toInstant(ZoneOffset.UTC);
    return Instant.now().isAfter(checkInBeginTime) && Instant.now().isBefore(checkInEndTime);

  }

  private void validateNewSubscription(Long userId, Long eventId) {
    if (subscriptionRepository.findOptionalByUserIdAndEventIdAndStatusIn(userId, eventId,
            Set.of(SubscriptionStatus.CONFIRMED.getCod(), SubscriptionStatus.PENDING.getCod()))
        .isPresent()) {
      throw new BusinessException("User already subscribed to this Event.");
    }
  }

  public Subscription confirmSubscription(Long id) {
    Subscription sub = findById(id);
    if (sub.getStatus() != SubscriptionStatus.PENDING) {
      throw new BusinessException("This subscription isn't a reservation.");
    }

    sub.setStatus(SubscriptionStatus.CONFIRMED);
    return subscriptionRepository.save(sub);
  }
}

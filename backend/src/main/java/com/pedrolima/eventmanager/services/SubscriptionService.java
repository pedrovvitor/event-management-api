package com.pedrolima.eventmanager.services;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedrolima.eventmanager.dto.SubscriptionDTO;
import com.pedrolima.eventmanager.entities.Event;
import com.pedrolima.eventmanager.entities.Subscription;
import com.pedrolima.eventmanager.entities.enums.SubscriptionStatus;
import com.pedrolima.eventmanager.exceptions.BusinessException;
import com.pedrolima.eventmanager.exceptions.ResourceNotFoundException;
import com.pedrolima.eventmanager.mapper.SubscriptionMapper;
import com.pedrolima.eventmanager.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {

	SubscriptionRepository subscriptionRepository;
	UserService userService;
	EventService eventService;
	SubscriptionMapper mapper;

	@Autowired
	public SubscriptionService(SubscriptionRepository subscriptionRepository, UserService userService,
			EventService eventService, SubscriptionMapper mapper) {
		this.subscriptionRepository = subscriptionRepository;
		this.userService = userService;
		this.eventService = eventService;
		this.mapper = mapper;
	}

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
		subscription.setUser(userService.findById(objDTO.getUserId()));
		subscription.setMoment(Instant.now());
		subscription.setStatus(status);

		return subscriptionRepository.save(subscription);
	}

	public Subscription cancelSubscription(Long id) {
		Subscription sub = findById(id);
		if (sub.isCheckedIn()) {
			throw new BusinessException("Unable to cancel subscription! User already checked-in to the Event!");
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
			throw new BusinessException("Check-in is closed!");
		}
		if (sub.getStatus() != SubscriptionStatus.CONFIRMED) {
			throw new BusinessException("Subscription not confirmed");
		}
		if (sub.isCheckedIn()) {
			throw new BusinessException("Subscription already checked in!");
		}

		sub.setCheckedIn(true);
		return subscriptionRepository.save(sub);

	}

	private boolean isCheckinOpen(Event event) {
		Instant checkInBeginTime = event.getBeginDateTime().toInstant(ZoneOffset.UTC).minus(1, ChronoUnit.HOURS);
		Instant checkInEndTime = event.getEndDateTime().toInstant(ZoneOffset.UTC);
		return Instant.now().isAfter(checkInBeginTime) && Instant.now().isBefore(checkInEndTime);

	}

	private void validateNewSubscription(Long userId, Long eventId) {
		if (subscriptionRepository.findOptionalByUserIdAndEventIdAndStatusIn(userId, eventId, Set.of(SubscriptionStatus.CONFIRMED.getCod(), SubscriptionStatus.PENDING.getCod())).isPresent()) {
			throw new BusinessException("User already subscribed at this Event");
		}

	}

	public Subscription confirmSubscription(Long id) {
		Subscription sub = findById(id);
		if (sub.getStatus() != SubscriptionStatus.PENDING) {
			throw new BusinessException("This subscription isn't a reservation");
		}

		sub.setStatus(SubscriptionStatus.CONFIRMED);
		return subscriptionRepository.save(sub);
	}

}

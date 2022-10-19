package com.pedrolima.eventmanager.mapper;

import com.pedrolima.eventmanager.dto.UserSubscriptionDTO;
import com.pedrolima.eventmanager.entities.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserSubscriptionMapper {

  EventMapper eventMapper;

  public UserSubscriptionDTO toDto(Subscription subscription) {
    return UserSubscriptionDTO.builder()
        .subscriptionId(subscription.getId())
        .isCheckedIn(subscription.isCheckedIn())
        .moment(subscription.getMoment())
        .status(subscription.getStatus())
        .event(eventMapper.toDTO(subscription.getEvent()))
        .build();
  }

  public Page<UserSubscriptionDTO> toDto(Page<Subscription> subscriptions) {
    return subscriptions.map(this::toDto);
  }
}

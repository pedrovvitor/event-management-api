package com.pedrolima.eventmanager.mapper;

import com.pedrolima.eventmanager.dto.UserSubscriptionDTO;
import com.pedrolima.eventmanager.entities.Subscription;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
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

	public Set<UserSubscriptionDTO> toDto(Set<Subscription> subscriptions) {
		return subscriptions.stream().map(this::toDto).collect(Collectors.toSet());
	}
}

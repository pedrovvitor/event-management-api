package com.pedrolima.eventmanager.mapper;

import com.pedrolima.eventmanager.dto.EventSubscriptionDTO;
import com.pedrolima.eventmanager.entities.Subscription;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventSubscriptionMapper {

	UserMapper userMapper;

	public EventSubscriptionDTO toDto(Subscription subscription) {
		return EventSubscriptionDTO.builder().subscriptionId(subscription.getId())
				.isCheckedIn(subscription.isCheckedIn())
				.moment(subscription.getMoment())
				.status(subscription.getStatus())
				.user(userMapper.toDTO(subscription.getUser()))
				.build();
	}

	public Set<EventSubscriptionDTO> toDto(Set<Subscription> subscriptions) {
		return subscriptions.stream().map(this::toDto).collect(Collectors.toSet());
	}
}

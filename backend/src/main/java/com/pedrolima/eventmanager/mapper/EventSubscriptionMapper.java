package com.pedrolima.eventmanager.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pedrolima.eventmanager.dto.EventSubscriptionDTO;
import com.pedrolima.eventmanager.entities.Subscription;

@Component
public class EventSubscriptionMapper {

	UserMapper userMapper;

	@Autowired
	public EventSubscriptionMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public EventSubscriptionDTO toDto(Subscription subscription) {
		EventSubscriptionDTO eventSubDto = new EventSubscriptionDTO();
		eventSubDto.setSubscriptionId(subscription.getId());
		eventSubDto.setCheckedIn(subscription.isCheckedIn());
		eventSubDto.setMoment(subscription.getMoment());
		eventSubDto.setStatus(subscription.getStatus());
		eventSubDto.setUser(userMapper.toDTO(subscription.getUser()));
		return eventSubDto;
	}

	public Set<EventSubscriptionDTO> toDto(Set<Subscription> subscriptions) {
		return subscriptions.stream().map(subscription -> {
			return toDto(subscription);
		}).collect(Collectors.toSet());
	}
}

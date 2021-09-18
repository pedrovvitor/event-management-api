package com.pedrolima.eventmanager.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pedrolima.eventmanager.dto.UserSubscriptionDTO;
import com.pedrolima.eventmanager.entities.Subscription;

@Component
public class UserSubscriptionMapper {

	EventMapper eventMapper;

	@Autowired
	public UserSubscriptionMapper(EventMapper eventMapper) {
		this.eventMapper = eventMapper;
	}

	public UserSubscriptionDTO toDto(Subscription subscription) {
		UserSubscriptionDTO userSubDto = new UserSubscriptionDTO();
		userSubDto.setSubscriptionId(subscription.getId());
		userSubDto.setCheckedIn(subscription.isCheckedIn());
		userSubDto.setMoment(subscription.getMoment());
		userSubDto.setStatus(subscription.getStatus());
		userSubDto.setEvent(eventMapper.toDTO(subscription.getEvent()));
		return userSubDto;
	}

	public Set<UserSubscriptionDTO> toDto(Set<Subscription> subscriptions) {
		return subscriptions.stream().map(subscription -> {
			return toDto(subscription);
		}).collect(Collectors.toSet());
	}
}

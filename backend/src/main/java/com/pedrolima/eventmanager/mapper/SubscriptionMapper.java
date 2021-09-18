package com.pedrolima.eventmanager.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pedrolima.eventmanager.dto.SubscriptionDTO;
import com.pedrolima.eventmanager.entities.Subscription;
import com.pedrolima.eventmanager.services.EventService;
import com.pedrolima.eventmanager.services.UserService;

@Component
public class SubscriptionMapper {

	@Autowired
	EventService eventService;

	@Autowired
	UserService userService;


	public Subscription toEntity(SubscriptionDTO dto) {

		Subscription entity = new Subscription();
		entity.setId(dto.getId());
		entity.setUser(userService.findById(dto.getUserId()));
		entity.setEvent(eventService.findById(dto.getEventId()));
		entity.setMoment(dto.getMoment());
		entity.setCheckedIn(dto.isCheckedIn());
		entity.setStatus(dto.getStatus());

		return entity;
	}

	public SubscriptionDTO toDTO(Subscription model) {

		SubscriptionDTO dto = new SubscriptionDTO();
		dto.setId(model.getId());
		dto.setEventId(model.getEvent().getId());
		dto.setUserId(model.getUser().getId());
		dto.setMoment(model.getMoment());
		dto.setCheckedIn(model.isCheckedIn());
		dto.setStatus(model.getStatus());

		return dto;
	}

}

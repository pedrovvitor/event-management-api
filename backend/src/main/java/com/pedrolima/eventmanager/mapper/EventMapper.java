package com.pedrolima.eventmanager.mapper;

import org.springframework.stereotype.Component;

import com.pedrolima.eventmanager.dto.EventDTO;
import com.pedrolima.eventmanager.entities.Event;

@Component
public class EventMapper {
	
	public Event toEntity(EventDTO dto) {

		Event entity = new Event();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setBeginDateTime(dto.getBeginDateAndTime());
		entity.setEndDateTime(dto.getEndDateAndTime());
		entity.setVacancies(dto.getVacancies());
		return entity;
	}

	public EventDTO toDTO(Event entity) {

		EventDTO dto = new EventDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setBeginDateAndTime(entity.getBeginDateTime());
		dto.setEndDateAndTime(entity.getEndDateTime());
		dto.setVacancies(entity.getVacancies());
		return dto;
	}

}

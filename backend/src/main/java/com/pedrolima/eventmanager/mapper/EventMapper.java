package com.pedrolima.eventmanager.mapper;

import com.pedrolima.eventmanager.dto.EventDTO;
import com.pedrolima.eventmanager.entities.Event;
import org.springframework.stereotype.Component;

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

    return EventDTO.builder()
        .id(entity.getId())
        .name(entity.getName())
        .beginDateAndTime(entity.getBeginDateTime())
        .endDateAndTime(entity.getEndDateTime())
        .vacancies(entity.getVacancies()).
        build();
  }
}

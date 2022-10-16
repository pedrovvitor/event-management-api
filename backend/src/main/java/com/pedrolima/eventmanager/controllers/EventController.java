package com.pedrolima.eventmanager.controllers;

import com.pedrolima.eventmanager.dto.EventDTO;
import com.pedrolima.eventmanager.dto.EventSubscriptionsDTO;
import com.pedrolima.eventmanager.entities.Event;
import com.pedrolima.eventmanager.mapper.EventMapper;
import com.pedrolima.eventmanager.services.EventService;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping(path = "events")
public class EventController {
	EventService eventService;
	EventMapper eventMapper;

	@GetMapping
	public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
		Page<EventDTO> result = eventService.findAll(pageable).map(event -> eventMapper.toDTO(event));
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EventDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(eventMapper.toDTO(eventService.findById(id)));
	}

	@GetMapping(path = "/subscriptions/{id}")
	public ResponseEntity<EventSubscriptionsDTO> findAllEventSubscriptions(@PathVariable Long id) {
		return ResponseEntity.ok(eventService.findAllEventSubscriptions(id));
	}

	@PostMapping
	public ResponseEntity<EventDTO> insert(@RequestBody EventDTO eventDto) {
		Event event = eventService.insert(eventDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(event.getId()).toUri();
		return ResponseEntity.created(uri).body(eventMapper.toDTO(event));
	}
}

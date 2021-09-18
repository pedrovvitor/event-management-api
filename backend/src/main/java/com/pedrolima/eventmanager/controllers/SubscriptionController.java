package com.pedrolima.eventmanager.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pedrolima.eventmanager.dto.SubscriptionDTO;
import com.pedrolima.eventmanager.entities.enums.SubscriptionStatus;
import com.pedrolima.eventmanager.mapper.SubscriptionMapper;
import com.pedrolima.eventmanager.services.SubscriptionService;

@RestController
@RequestMapping(path = "/subscriptions")
public class SubscriptionController {

	SubscriptionService subscriptionService;
	SubscriptionMapper subscriptionMapper;

	@Autowired
	public SubscriptionController(SubscriptionService subscriptionService, SubscriptionMapper subscriptionMapper) {
		this.subscriptionService = subscriptionService;
		this.subscriptionMapper = subscriptionMapper;
	}

	@GetMapping
	public ResponseEntity<Page<SubscriptionDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(subscriptionService.findAll(pageable).map(sub -> subscriptionMapper.toDTO(sub)));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<SubscriptionDTO> findById(@PathVariable String id) {
		return ResponseEntity.ok(subscriptionMapper.toDTO(subscriptionService.findById(id)));
	}

	@PostMapping
	public ResponseEntity<SubscriptionDTO> insert(@RequestBody SubscriptionDTO objDTO) {
		SubscriptionDTO sub = subscriptionMapper
				.toDTO(subscriptionService.insert(objDTO, SubscriptionStatus.CONFIRMED));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sub.getId()).toUri();
		return ResponseEntity.created(uri).body(sub);
	}

	@PostMapping(path = "/reservation")
	public ResponseEntity<SubscriptionDTO> reservation(@RequestBody SubscriptionDTO objDTO) {
		SubscriptionDTO sub = subscriptionMapper.toDTO(subscriptionService.insert(objDTO, SubscriptionStatus.PENDING));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sub.getId()).toUri();
		return ResponseEntity.created(uri).body(sub);
	}

	@PatchMapping(path = "/confirm/{id}")
	public ResponseEntity<SubscriptionDTO> confirmSubscription(@PathVariable String id) {

		return ResponseEntity.ok(subscriptionMapper.toDTO(subscriptionService.confirmSubscription(id)));
	}

	@PatchMapping(path = "/cancel/{id}")
	public ResponseEntity<SubscriptionDTO> cancelSubscription(@PathVariable String id) {

		return ResponseEntity.ok(subscriptionMapper.toDTO(subscriptionService.cancelSubscription(id)));
	}

	@PatchMapping(path = "/checkin/{id}")
	public ResponseEntity<SubscriptionDTO> checkIn(@PathVariable String id) {

		return ResponseEntity.ok(subscriptionMapper.toDTO(subscriptionService.checkIn(id)));
	}
}

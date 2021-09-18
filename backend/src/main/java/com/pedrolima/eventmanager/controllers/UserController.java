package com.pedrolima.eventmanager.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.pedrolima.eventmanager.dto.UserDTO;
import com.pedrolima.eventmanager.dto.UserSubscriptionsDTO;
import com.pedrolima.eventmanager.entities.User;
import com.pedrolima.eventmanager.mapper.UserMapper;
import com.pedrolima.eventmanager.services.UserService;

@RestController
@RequestMapping(path = "users")
public class UserController {

	UserService userService;
	UserMapper userMapper;

	@Autowired
	public UserController(UserService userService, UserMapper userMapper) {
		super();
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping
	public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {

		return ResponseEntity.ok(userService.findAll(pageable).map(user -> userMapper.toDTO(user)));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(userMapper.toDTO(userService.findById(id)));
	}

	@GetMapping(path = "/subscriptions/{id}")
	public ResponseEntity<UserSubscriptionsDTO> findAllUserSubscriptions(@PathVariable Long id) {
		return ResponseEntity.ok(userService.findAllUserSubscriptions(id));
	}

	@PostMapping
	public ResponseEntity<User> insert(@RequestBody UserDTO userDto) {
		User user = userService.insert(userMapper.toEntity(userDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

}

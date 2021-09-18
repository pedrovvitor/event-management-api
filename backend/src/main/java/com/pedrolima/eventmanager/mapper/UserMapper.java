package com.pedrolima.eventmanager.mapper;

import org.springframework.stereotype.Component;

import com.pedrolima.eventmanager.dto.UserDTO;
import com.pedrolima.eventmanager.entities.User;

@Component
public class UserMapper {

	public User toEntity(UserDTO dto) {

		User entity = new User();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	public UserDTO toDTO(User entity) {

		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}
}

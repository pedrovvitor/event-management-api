package com.pedrolima.eventmanager.mapper;

import com.pedrolima.eventmanager.dto.UserDTO;
import com.pedrolima.eventmanager.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toEntity(UserDTO dto) {
    if (dto == null) {
      return null;
    }
    User entity = new User();
    entity.setId(dto.getId());
    entity.setFirstName(dto.getName());
    return entity;
  }

  public UserDTO toDTO(User entity) {
    if (entity == null) {
      return null;
    }
    UserDTO dto = new UserDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getFirstName());
    return dto;
  }
}

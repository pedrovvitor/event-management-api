package com.pedrolima.eventmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedrolima.eventmanager.dto.UserSubscriptionsDTO;
import com.pedrolima.eventmanager.entities.User;
import com.pedrolima.eventmanager.exceptions.ResourceNotFoundException;
import com.pedrolima.eventmanager.mapper.UserMapper;
import com.pedrolima.eventmanager.mapper.UserSubscriptionMapper;
import com.pedrolima.eventmanager.repositories.SubscriptionRepository;
import com.pedrolima.eventmanager.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserSubscriptionMapper userSubscriptionMapper;

	public Page<User> findAll(Pageable pageable) {

		return userRepository.findAll(pageable);
	}

	public User findById(String id) {
		return  userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for Id: " + id));
	}

	public User insert(User user) {
		
		return userRepository.save(user);
	}

	public UserSubscriptionsDTO findAllUserSubscriptions(String id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for Id: " + id));
		UserSubscriptionsDTO responseDto = new UserSubscriptionsDTO();
		responseDto.setId(user.getId());
		responseDto.setName(user.getName());
		responseDto.setSubscriptions(userSubscriptionMapper.toDto(user.getSubscriptions()));
		return responseDto;
	}

}

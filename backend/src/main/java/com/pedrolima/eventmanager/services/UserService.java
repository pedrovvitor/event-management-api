package com.pedrolima.eventmanager.services;

import com.pedrolima.eventmanager.dto.UserSubscriptionsDTO;
import com.pedrolima.eventmanager.entities.User;
import com.pedrolima.eventmanager.exceptions.ResourceNotFoundException;
import com.pedrolima.eventmanager.mapper.UserMapper;
import com.pedrolima.eventmanager.mapper.UserSubscriptionMapper;
import com.pedrolima.eventmanager.repositories.SubscriptionRepository;
import com.pedrolima.eventmanager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

	UserRepository userRepository;
	SubscriptionRepository subscriptionRepository;
	UserMapper userMapper;
	UserSubscriptionMapper userSubscriptionMapper;

	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {

		return userRepository.findAll(pageable);
	}

	public User findById(Long id) {
		return  userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for Id: " + id));
	}

	public User insert(User user) {
		
		return userRepository.save(user);
	}

	public UserSubscriptionsDTO findAllUserSubscriptions(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for Id: " + id));
		UserSubscriptionsDTO responseDto = new UserSubscriptionsDTO();
		responseDto.setId(user.getId());
		responseDto.setName(user.getFirstName());
		responseDto.setSubscriptions(userSubscriptionMapper.toDto(user.getSubscriptions()));
		return responseDto;
	}
}

package com.pedrolima.eventmanager;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pedrolima.eventmanager.entities.Event;
import com.pedrolima.eventmanager.entities.Subscription;
import com.pedrolima.eventmanager.entities.User;
import com.pedrolima.eventmanager.repositories.EventRepository;
import com.pedrolima.eventmanager.repositories.SubscriptionRepository;
import com.pedrolima.eventmanager.repositories.UserRepository;

@SpringBootApplication
public class EventmanagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EventmanagerApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	EventRepository eventRepository;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Pedro Lima");
		User user2 = new User(null, "Augusto dos Anjos");
		User user3 = new User(null, "Mario Andreazza");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		Event event1 = new Event(null, "Desafio BeHoh", 10, LocalDateTime.of(2021, 9, 15, 10, 00), LocalDateTime.of(2021, 9, 19, 10, 00));
		Event event2 = new Event(null, "Evento Teste", 10, LocalDateTime.of(2021, 9, 15, 10, 00), LocalDateTime.of(2021, 9, 19, 10, 00));
		
		eventRepository.saveAll(Arrays.asList(event1, event2));
		
		Subscription sub1 = new Subscription(event1, user1, Instant.now(), 1, false);
		
		subscriptionRepository.save(sub1);
	}

}

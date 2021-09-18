package com.pedrolima.eventmanager;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

	UserRepository userRepository;
	EventRepository eventRepository;
	SubscriptionRepository subscriptionRepository;

	@Autowired
	public EventmanagerApplication(UserRepository userRepository, EventRepository eventRepository,
			SubscriptionRepository subscriptionRepository) {
		this.userRepository = userRepository;
		this.eventRepository = eventRepository;
		this.subscriptionRepository = subscriptionRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Pedro Lima");
		User user2 = new User(null, "Augusto dos Anjos");
		User user3 = new User(null, "Mario Andreazza");
		User user4 = new User(null, "Alirio");

		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));

		Event event1 = new Event(null, "Desafio BeHoh", 10, LocalDateTime.of(2021, 9, 15, 10, 00),
				LocalDateTime.of(2021, 9, 19, 10, 00));
		Event event2 = new Event(null, "Evento Aleatorio", 10, LocalDateTime.of(2021, 10, 20, 10, 00),
				LocalDateTime.of(2021, 10, 30, 10, 00));
		Event event3 = new Event(null, "Evento Cheio para cancelar inscrição", 0, LocalDateTime.of(2021, 10, 20, 10, 00),
				LocalDateTime.of(2021, 10, 30, 10, 00));

		eventRepository.saveAll(Arrays.asList(event1, event2, event3));

		Subscription sub1 = new Subscription(event1, user1, LocalDateTime.of(2021, 9, 16, 10, 00).toInstant(ZoneOffset.UTC), 1, false);
		Subscription sub2 = new Subscription(event2, user2, LocalDateTime.of(2021, 10, 21, 10, 00).toInstant(ZoneOffset.UTC), 1, false);
		Subscription sub3 = new Subscription(event3, user3, LocalDateTime.of(2021, 10, 23, 10, 00).toInstant(ZoneOffset.UTC), 1, false);

		subscriptionRepository.saveAll(Arrays.asList(sub1, sub2, sub3));
	}

}

package com.pedrolima.eventmanager.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrolima.eventmanager.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

	Optional<Subscription> findOptionalByUserIdAndEventIdAndStatusIn(String userId, String eventId, Set<Integer> status);

}

package com.pedrolima.eventmanager.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrolima.eventmanager.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	Optional<Subscription> findOptionalByUserIdAndEventIdAndStatusIn(Long userId, Long eventId, Set<Integer> status);

}

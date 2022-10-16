package com.pedrolima.eventmanager.repositories;

import com.pedrolima.eventmanager.entities.Subscription;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	Optional<Subscription> findOptionalByUserIdAndEventIdAndStatusIn(Long userId, Long eventId, Set<Integer> status);

}

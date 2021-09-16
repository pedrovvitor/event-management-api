package com.pedrolima.eventmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrolima.eventmanager.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}

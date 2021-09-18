package com.pedrolima.eventmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrolima.eventmanager.entities.Event;

public interface EventRepository extends JpaRepository<Event, String> {

}

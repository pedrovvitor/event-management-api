package com.pedrolima.eventmanager.repositories;

import com.pedrolima.eventmanager.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}

package com.pedrolima.eventmanager.repositories;

import com.pedrolima.eventmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

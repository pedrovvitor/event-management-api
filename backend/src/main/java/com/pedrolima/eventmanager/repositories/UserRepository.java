package com.pedrolima.eventmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrolima.eventmanager.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}

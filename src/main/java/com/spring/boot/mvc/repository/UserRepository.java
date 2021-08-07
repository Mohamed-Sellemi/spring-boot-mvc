package com.spring.boot.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.mvc.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}

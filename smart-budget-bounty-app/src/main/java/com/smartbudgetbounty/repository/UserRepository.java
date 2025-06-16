package com.smartbudgetbounty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudgetbounty.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
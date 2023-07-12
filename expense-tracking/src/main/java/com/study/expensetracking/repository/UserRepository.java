package com.study.expensetracking.repository;

import com.study.expensetracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String email);

    Optional<Object> findByUsername(String username);

    boolean existsByUsername(String username);
}

package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    List<User> findByNameContaining(String keyword);
}

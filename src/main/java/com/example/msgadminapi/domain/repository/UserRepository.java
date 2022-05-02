package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}

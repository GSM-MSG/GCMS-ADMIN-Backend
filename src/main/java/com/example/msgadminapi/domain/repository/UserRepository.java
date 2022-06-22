package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.configuration.security.auth.MyUserDetails;
import com.example.msgadminapi.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    List<User> findAllByNameLike(String keyword);

     Optional<User> findUserByEmail(String email);
}

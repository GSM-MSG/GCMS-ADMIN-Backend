package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.dto.response.ClubResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {

    List<ClubResponseDto> findByTitleContaining(String title);
}

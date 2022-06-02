package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.dto.response.ClubResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    public List<Club> findAllByTitleLike(String title);
}

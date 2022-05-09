package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.repository.ClubRepository;
import com.example.msgadminapi.dto.request.ClubTitleModifyRequest;
import com.example.msgadminapi.dto.response.ClubResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public List<ClubResponseDto> findAll() {
        List<ClubResponseDto> clubs = new ArrayList<>();
        clubRepository.findAll().forEach(e -> clubs.add(ClubResponseDto.builder()
                .id(e.getId())
                .type(e.getType())
                .bannerUrl(e.getBannerUrl())
                .title(e.getTitle())
                .description(e.getDescription())
                .contact(e.getContact())
                .teacher(e.getTeacher())
                .isOpened(e.isOpened())
                .build()));
        return clubs;
    }


}

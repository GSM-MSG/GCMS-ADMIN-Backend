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


    @Transactional
    public List<ClubResponseDto> search(String title) {
        List<ClubResponseDto> searchList = new ArrayList<>();
        clubRepository.findByTitleContaining(title)
                .forEach(e -> searchList.add(ClubResponseDto.builder()
                        .id(e.getId())
                        .type(e.getType())
                        .bannerUrl(e.getBannerUrl())
                        .title(e.getTitle())
                        .description(e.getDescription())
                        .contact(e.getContact())
                        .teacher(e.getTeacher())
                        .isOpened(e.isOpened())
                        .build()));
        return searchList;
    }

    @Transactional
    public void clubTitleModify(ClubTitleModifyRequest request) {
        Optional<Club> clubEntity = clubRepository.findById(request.getId());
        clubEntity.ifPresent(e -> e.titleModify(request.getTitle()));
    }

    public void clubDelete(String clubIdx) throws Exception {
        Long id = Long.parseLong(clubIdx);
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new Exception("Club is Not Found "));
        clubRepository.delete(club);
    }

}

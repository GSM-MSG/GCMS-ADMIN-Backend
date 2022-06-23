package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.repository.ClubRepository;
import com.example.msgadminapi.dto.request.ClubTitleModifyRequest;
import com.example.msgadminapi.dto.response.ClubResponseDto;
import com.example.msgadminapi.exception.ErrorCode;
import com.example.msgadminapi.exception.exception.ClubNotFindException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Transactional(readOnly = true)
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
                .isOpened(e.getIsOpened())
                .build()));
        return clubs;
    }


    @Transactional(readOnly = true)
    public List<ClubResponseDto> search(String title) {
        List<Club> allByTitleLike = clubRepository.findAllByTitleLike("%"+title+"%");
        List<ClubResponseDto> response = getClubResponseDtoList(allByTitleLike);

        return response;
    }

    @Transactional
    public void clubTitleModify(ClubTitleModifyRequest request) {
        Club clubEntity = clubRepository.findById(request.getId())
                        .orElseThrow(() -> new ClubNotFindException());
        clubEntity.titleModify(request.getTitle());
    }

    @Transactional
    public void clubDelete(Long clubIdx) {
        Club club = clubRepository.findById(clubIdx)
                .orElseThrow(() -> new ClubNotFindException());
        clubRepository.delete(club);
    }

    @Transactional
    public void clubClose(Long clubIdx) {
        Club club = clubRepository.findById(clubIdx)
                .orElseThrow(() -> new ClubNotFindException());
        club.isClubFinishOpen(false);
    }

    public List<ClubResponseDto> getClubResponseDtoList(List<Club> all) {
        List<ClubResponseDto> searchList = new ArrayList<>();
        all.forEach(e -> searchList.add(ClubResponseDto.builder()
                .id(e.getId())
                .type(e.getType())
                .bannerUrl(e.getBannerUrl())
                .title(e.getTitle())
                .description(e.getDescription())
                .contact(e.getContact())
                .teacher(e.getTeacher())
                .isOpened(e.getIsOpened())
                .build()));
        return searchList;
    }
}

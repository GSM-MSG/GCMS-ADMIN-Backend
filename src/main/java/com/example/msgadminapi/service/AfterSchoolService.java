package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.afterschool.DayOfWeek;
import com.example.msgadminapi.domain.entity.afterschool.Grade;
import com.example.msgadminapi.domain.entity.afterschool.enums.Season;
import com.example.msgadminapi.domain.entity.classregistration.ClassRegistration;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.domain.repository.*;
import com.example.msgadminapi.dto.request.AfterSchoolDto;
import com.example.msgadminapi.dto.request.AfterSchoolModifyDto;
import com.example.msgadminapi.dto.response.StatisticsResponseDto;
import com.example.msgadminapi.exception.exception.AfterSchoolNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AfterSchoolService {
    private final AfterSchoolRepository afterSchoolRepository;
    private final DayOfWeekRepository dayOfWeekRepository;
    private final GradeRepository gradeRepository;
    private final ClassRegistrationRepository classRegistrationRepository;

    @Transactional(readOnly = true)
    public List<AfterSchool> findAll(){
        return afterSchoolRepository.findAll();
    }

    @Transactional
    public void updateAfterSchool(Long afterSchoolIdx, AfterSchoolModifyDto afterSchoolDto){
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolIdx)
                .orElseThrow(() -> new AfterSchoolNotFoundException());
        afterSchool.update(afterSchoolDto, gradeRepository, dayOfWeekRepository);
    }

    @Transactional(readOnly = true)
    public List<User> findUserByAfterSchool(Long afterSchoolIdx){
        List<User> userList = new ArrayList<>();
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolIdx)
                .orElseThrow(() -> new AfterSchoolNotFoundException());
        List<ClassRegistration> allByAfterSchool = classRegistrationRepository.findAllByAfterSchool(afterSchool);
        allByAfterSchool.forEach(e -> userList.add(e.getUser()));
        return userList;
    }

    @Transactional
    public void createAfterSchool(AfterSchoolDto afterSchoolDto){
        List<DayOfWeek> weeks = new ArrayList<>();
        List<Grade> grades = new ArrayList<>();
        afterSchoolDto.getDayOfWeek().forEach(e->weeks.add(DayOfWeek.builder().dayOfWeek(e).build()));
        afterSchoolDto.getGrade().forEach(e->grades.add(Grade.builder().grade(e).build()));
        AfterSchool afterSchool = afterSchoolDto.toEntity(grades, weeks);
        grades.forEach(e->e.mapping(afterSchool));
        weeks.forEach(e->e.mapping(afterSchool));
        gradeRepository.saveAll(grades);
        dayOfWeekRepository.saveAll(weeks);
        afterSchoolRepository.save(afterSchool);
    }

    @Transactional
    public void deleteAfterSchool(Long afterSchoolIdx){
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolIdx)
                .orElseThrow(() -> new AfterSchoolNotFoundException());
        afterSchoolRepository.delete(afterSchool);
    }

    @Transactional(readOnly = true)
    public List<StatisticsResponseDto> getStatistics(){
        List<StatisticsResponseDto> list = new ArrayList<>();
        afterSchoolRepository.findAll()
                .forEach(e -> list.add(
                        StatisticsResponseDto.builder()
                            .afterSchoolIdx(e.getId())
                            .afterSchoolTitle(e.getTitle())
                            .total(e.getClassRegistration().size())
                            .build()
                ));
        return list;
    }

    @Transactional
    public void closeAllAfterSchool(Season season, Long year) {
        afterSchoolRepository.findAllBySeasonAndYearOf(season, year).forEach(after -> {
            if(after.getIsOpened() == true) {
                after.changeIsOpened(false);
            }
        });
    }

    @Transactional
    public void closeAfterSchool(Long afterSchoolIdx, Season season, Long year) {
        AfterSchool afterSchool = afterSchoolRepository.findByIdAndSeasonAndYearOf(afterSchoolIdx, season, year)
                        .orElseThrow(() -> new AfterSchoolNotFoundException());
        if(afterSchool.getIsOpened()) {
            afterSchool.changeIsOpened(false);
        }
    }

    @Transactional
    public void openAllAfterSchool(Season season, Long year) {
        afterSchoolRepository.findAllBySeasonAndYearOf(season, year).stream().filter(after -> !after.getIsOpened()).forEach(after -> {
                after.changeIsOpened(true);
        });
    }

    @Transactional
    public void openAfterSchool(Long afterSchoolIdx, Season season, Long year) {
         AfterSchool afterSchool = afterSchoolRepository.findByIdAndSeasonAndYearOf(afterSchoolIdx, season, year)
                 .orElseThrow(() -> new AfterSchoolNotFoundException());
        if(!afterSchool.getIsOpened()) {
            afterSchool.changeIsOpened(true);
        }
    }

    @Transactional
    public void deleteApplyMember(Long afterSchoolId, String userEmail){
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolId)
                .orElseThrow(() -> new AfterSchoolNotFindException("방과후 신청을 받아주는 도중 방과후를 찾지 못했습니다.", ErrorCode.AFTERSCHOOL_NOT_FIND));
        afterSchool.getClassRegistration()
                .stream()
                .filter(classRegistration -> classRegistration.getUser().getEmail().equals(userEmail))
                .forEach(classRegistration -> classRegistrationRepository.delete(classRegistration));
    }
}

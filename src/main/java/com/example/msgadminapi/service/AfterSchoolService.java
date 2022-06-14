package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.afterschool.DayOfWeek;
import com.example.msgadminapi.domain.entity.afterschool.Grade;
import com.example.msgadminapi.domain.entity.classregistration.ClassRegistration;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.domain.repository.AfterSchoolRepository;
import com.example.msgadminapi.domain.repository.ClassRegistrationRepository;
import com.example.msgadminapi.domain.repository.DayOfWeekRepository;
import com.example.msgadminapi.domain.repository.GradeRepository;
import com.example.msgadminapi.dto.request.AfterSchoolDto;
import com.example.msgadminapi.dto.request.AfterSchoolModifyDto;
import com.example.msgadminapi.dto.response.StatisticsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
                .orElseThrow(() -> new RuntimeException());
        afterSchool.update(afterSchoolDto, gradeRepository, dayOfWeekRepository);
    }

    @Transactional(readOnly = true)
    public List<User> findUserByAfterSchool(Long afterSchoolIdx){
        List<User> userList = new ArrayList<>();
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolIdx)
                .orElseThrow(() -> new RuntimeException());
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
                .orElseThrow(() -> new RuntimeException());
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
        afterSchoolRepository.findAllBySeasonAndYearOf(season, year).forEach(e -> {
            if(e.getIsOpened() == true) {
                e.changeIsOpened(false);
            }
        });
    }

    @Transactional
    public void closeAfterSchool(Long afterSchoolIdx, Season season, Long year) {
        AfterSchool afterSchool = afterSchoolRepository.findByIdAndSeasonAndYearOf(afterSchoolIdx, season, year)
                        .orElseThrow(() -> new RuntimeException("값을 찾지 못함"));
        if(afterSchool.getIsOpened() == true) {
            afterSchool.changeIsOpened(false);
        }
    }

    @Transactional
    public void openAllAfterSchool(Season season, Long year) {
        afterSchoolRepository.findAllBySeasonAndYearOf(season, year).forEach(e -> {
            if(e.getIsOpened() == false) {
                e.changeIsOpened(true);
            }
        });
    }

}

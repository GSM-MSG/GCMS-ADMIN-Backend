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
import com.example.msgadminapi.dto.response.AfterSchoolFindResponseDto;
import com.example.msgadminapi.dto.response.StatResponseDto;
import com.example.msgadminapi.dto.response.StatisticsResponseDto;
import com.example.msgadminapi.exception.exception.AfterSchoolNotFoundException;
import com.example.msgadminapi.exception.exception.AfterSchoolReduplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AfterSchoolService {
    private final AfterSchoolRepository afterSchoolRepository;
    private final DayOfWeekRepository dayOfWeekRepository;
    private final GradeRepository gradeRepository;
    private final ClassRegistrationRepository classRegistrationRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<AfterSchoolFindResponseDto> findAll(){
        List<AfterSchoolFindResponseDto> list = new ArrayList<>();
        afterSchoolRepository.findAll()
                .forEach(e -> list.add(
                        AfterSchoolFindResponseDto.builder()
                                .id(e.getId())
                                .title(e.getTitle())
                                .dayOfWeek((e.getDayOfWeek().stream()
                                        .map(DayOfWeek::getDayOfWeek)
                                        .collect(Collectors.toList()))
                                )
                                .grade((e.getGrade().stream()
                                        .map(Grade::getGrade)
                                        .collect(Collectors.toList())))
                                .teacher(e.getTeacher())
                                .season(e.getSeason())
                                .yearOf(e.getYearOf())
                                .isOpened(e.getIsOpened())
                                .build()
                ));
        return list;
    }

    @Transactional
    public void updateAfterSchool(Integer afterSchoolIdx, AfterSchoolModifyDto afterSchoolDto){
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolIdx)
                .orElseThrow(() -> new AfterSchoolNotFoundException());
        afterSchool.update(afterSchoolDto, gradeRepository, dayOfWeekRepository);
    }

    @Transactional(readOnly = true)
    public List<User> findUserByAfterSchool(Integer afterSchoolIdx){
        List<User> userList = new ArrayList<>();
        System.out.println("afterSchoolIdx = " + afterSchoolIdx);
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolIdx)
                .orElseThrow(() -> new AfterSchoolNotFoundException());
        List<ClassRegistration> allByAfterSchool = classRegistrationRepository.findAllByAfterSchool(afterSchool);
        allByAfterSchool.forEach(e -> userList.add(e.getUser()));
        return userList;
    }

    @Transactional
    public AfterSchoolFindResponseDto createAfterSchool(AfterSchoolDto afterSchoolDto){
        List<DayOfWeek> weeks = new ArrayList<>();
        List<Grade> grades = new ArrayList<>();
        if(afterSchoolRepository.existsByTitleAndSeasonAndYearOf(afterSchoolDto.getTitle(), afterSchoolDto.getSeason(), afterSchoolDto.getYearOf())) {
            throw new AfterSchoolReduplicationException();
        }
        afterSchoolDto.getDayOfWeek().forEach(e->weeks.add(DayOfWeek.builder().dayOfWeek(e).build()));
        afterSchoolDto.getGrade().forEach(e->grades.add(Grade.builder().grade(e).build()));
        AfterSchool afterSchool = afterSchoolDto.toEntity(grades, weeks);
        grades.forEach(e->e.mapping(afterSchool));
        weeks.forEach(e->e.mapping(afterSchool));
        gradeRepository.saveAll(grades);
        dayOfWeekRepository.saveAll(weeks);
        afterSchoolRepository.save(afterSchool);
        AfterSchool afterTitleAndSeasonAndYearOf = afterSchoolRepository.findByTitleAndSeasonAndYearOf(afterSchoolDto.getTitle(), afterSchoolDto.getSeason(), afterSchoolDto.getYearOf())
                .orElseThrow(() -> new AfterSchoolNotFoundException());
        AfterSchoolFindResponseDto afterResponse = AfterSchoolFindResponseDto.builder()
                .id(afterTitleAndSeasonAndYearOf.getId())
                .title(afterTitleAndSeasonAndYearOf.getTitle())
                .teacher(afterTitleAndSeasonAndYearOf.getTeacher())
                .season(afterTitleAndSeasonAndYearOf.getSeason())
                .yearOf(afterTitleAndSeasonAndYearOf.getYearOf())
                .isOpened(afterTitleAndSeasonAndYearOf.getIsOpened())
                .dayOfWeek(new ArrayList<>(afterTitleAndSeasonAndYearOf.getDayOfWeek().stream()
                        .map(d->d.getDayOfWeek())
                        .collect(Collectors.toList()))
                )
                .grade(new ArrayList<>(afterTitleAndSeasonAndYearOf.getGrade().stream()
                        .map(g -> g.getGrade())
                        .collect(Collectors.toList()))
                )
                .build();
        return afterResponse;
    }

    @Transactional
    public void deleteAfterSchool(Integer afterSchoolIdx){
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolIdx)
                .orElseThrow(() -> new AfterSchoolNotFoundException());
        afterSchoolRepository.delete(afterSchool);
    }

    @Transactional(readOnly = true)
    public StatResponseDto getStatistics(){
        List<StatisticsResponseDto> list = new ArrayList<>();
        afterSchoolRepository.findAll()
                .forEach(e -> list.add(
                        StatisticsResponseDto.builder()
                            .afterSchoolIdx(e.getId())
                            .afterSchoolTitle(e.getTitle())
                            .dayOfWeekList(new ArrayList<>(e.getDayOfWeek().stream()
                                    .map(d->d.getDayOfWeek())
                                    .collect(Collectors.toList()))
                            )
                            .grade(new ArrayList<>(e.getGrade().stream()
                                    .map(g->g.getGrade())
                                    .collect(Collectors.toList())))
                            .attend(e.getClassRegistration().size())
                            .build()
                ));
        StatResponseDto build = StatResponseDto.builder()
                .total(userRepository.findAll().size())
                .afterSchools(list)
                .build();
        return build;
    }

    @Transactional
    public void closeAllAfterSchool(Season season, Integer year) {
        afterSchoolRepository.findAllBySeasonAndYearOf(season, year).forEach(after -> {
            if(after.getIsOpened() == true) {
                after.changeIsOpened(false);
            }
        });
    }

    @Transactional
    public void closeAfterSchool(Integer afterSchoolIdx) {
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolIdx)
                        .orElseThrow(() -> new AfterSchoolNotFoundException());
        if(afterSchool.getIsOpened()) {
            afterSchool.changeIsOpened(false);
        }
    }

    @Transactional
    public void openAllAfterSchool(Season season, Integer year) {
        afterSchoolRepository.findAllBySeasonAndYearOf(season, year).stream().filter(after -> !after.getIsOpened()).forEach(after -> {
                after.changeIsOpened(true);
        });
    }

    @Transactional
    public void openAfterSchool(Integer afterSchoolIdx) {
         AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolIdx)
                 .orElseThrow(() -> new AfterSchoolNotFoundException());
        if(!afterSchool.getIsOpened()) {
            afterSchool.changeIsOpened(true);
        }
    }

    @Transactional
    public void deleteApplyMember(Integer afterSchoolId, String userEmail){
        AfterSchool afterSchool = afterSchoolRepository.findById(afterSchoolId)
                .orElseThrow(() -> new AfterSchoolNotFoundException());
        afterSchool.getClassRegistration()
                .stream()
                .filter(classRegistration -> classRegistration.getUser().getEmail().equals(userEmail))
                .forEach(classRegistration -> classRegistrationRepository.delete(classRegistration));
    }
}

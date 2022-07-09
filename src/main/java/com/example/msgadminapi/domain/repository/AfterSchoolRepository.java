package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.afterschool.enums.Season;
import org.junit.After;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface AfterSchoolRepository extends JpaRepository<AfterSchool, Integer> {
    List<AfterSchool> findAllBySeasonAndYearOf(Season season, Integer year);
    Optional<AfterSchool>findByIdAndSeasonAndYearOf(Integer id, Season season, Integer year);
    Optional<AfterSchool> findByTitleAndSeasonAndYearOf(String title, Season season, Integer year);
    boolean existsByTitleAndSeasonAndYearOf(String title, Season season, Integer year);

}

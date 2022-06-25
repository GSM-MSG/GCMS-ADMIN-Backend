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
public interface AfterSchoolRepository extends JpaRepository<AfterSchool, Long> {
    List<AfterSchool> findAllBySeasonAndYearOf(Season season, Long year);
    Optional<AfterSchool>findByIdAndSeasonAndYearOf(Long id, Season season, Long year);

}

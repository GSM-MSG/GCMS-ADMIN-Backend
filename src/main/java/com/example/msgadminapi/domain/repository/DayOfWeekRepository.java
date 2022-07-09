package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.afterschool.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Integer> {
    List<DayOfWeek> findByAfterSchool_Id(Integer id);
}

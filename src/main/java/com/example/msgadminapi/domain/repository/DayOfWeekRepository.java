package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.afterschool.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Long> {
}

package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AfterSchoolRepository extends JpaRepository<AfterSchool, Long> {
}

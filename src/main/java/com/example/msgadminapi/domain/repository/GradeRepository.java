package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.afterschool.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
}

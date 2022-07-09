package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.afterschool.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findByAfterSchool_Id(Integer id);
}

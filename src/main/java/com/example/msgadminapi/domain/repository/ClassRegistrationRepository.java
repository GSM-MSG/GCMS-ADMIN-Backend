package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.classregistration.ClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRegistrationRepository  extends JpaRepository<ClassRegistration, Integer> {
    List<ClassRegistration> findAllByAfterSchool(AfterSchool afterSchool);
}

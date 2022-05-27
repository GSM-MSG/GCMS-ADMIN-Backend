package com.example.msgadminapi.domain.entity.afterschool;

import com.example.msgadminapi.domain.entity.afterschool.enums.Grade;
import com.example.msgadminapi.domain.entity.afterschool.enums.Season;
import com.example.msgadminapi.domain.entity.afterschool.enums.Week;
import com.example.msgadminapi.domain.entity.classregistration.ClassRegistration;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class AfterSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Week dayOfWeek;

    private Grade grade;

    private String teacher;

    @Column(columnDefinition = "TINYINT")
    private Boolean canDuplicate;

    @Column(columnDefinition = "TINYINT")
    private Boolean isCommon;

    private Long maxPersonnel;

    @Column(columnDefinition = "TINYINT")
    private Boolean isFull;

    @Enumerated(EnumType.STRING)
    private Season season;

    private Long yearOf;

    @Column(columnDefinition = "TINYINT")
    private Boolean isOpened;

    @OneToMany(mappedBy = "afterSchool", fetch = FetchType.EAGER)
    private Set<ClassRegistration> classRegistration = new HashSet<>();
}

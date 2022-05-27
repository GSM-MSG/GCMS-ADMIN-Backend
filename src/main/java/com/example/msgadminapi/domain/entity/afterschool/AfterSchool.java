package com.example.msgadminapi.domain.entity.afterschool;

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

    private Long personnel;

    private String week;

    private Long grade;

    private String teacher;

    @Column(columnDefinition = "TINYINT")
    private Boolean canDuplicate;

    @Column(columnDefinition = "TINYINT")
    private Boolean isCommon;

    private Long maxPersonnel;

    @Column(columnDefinition = "TINYINT")
    private Boolean isFull;

    private String season;

    private Long year;

    @Column(columnDefinition = "TINYINT")
    private Boolean isOpened;

    @OneToMany(mappedBy = "afterSchool", fetch = FetchType.EAGER)
    private Set<ClassRegistration> classRegistration = new HashSet<>();
}

package com.example.msgadminapi.domain.entity.afterschool;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="afterSchoolList")
public class AfterSchoolList {
    @Id @GeneratedValue
    @Column(name = "afterSchoolList_id")
    private Long id;

    @ManyToOne
    private Grade grade;

    @ManyToOne
    private AfterSchool  afterSchool;

    @ManyToOne
    private Week week;
}

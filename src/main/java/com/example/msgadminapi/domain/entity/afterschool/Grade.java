package com.example.msgadminapi.domain.entity.afterschool;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterschool_id")
    private AfterSchool afterSchool;

    private Long grade;
}

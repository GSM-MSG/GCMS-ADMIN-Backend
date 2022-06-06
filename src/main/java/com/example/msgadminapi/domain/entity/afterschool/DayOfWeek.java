package com.example.msgadminapi.domain.entity.afterschool;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class DayOfWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterschool_id")
    private AfterSchool afterSchool;

    private String dayOfWeek;
}

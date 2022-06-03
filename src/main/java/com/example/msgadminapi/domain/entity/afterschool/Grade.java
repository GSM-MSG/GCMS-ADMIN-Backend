package com.example.msgadminapi.domain.entity.afterschool;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Grade {
    @Id
    private String grade;
}

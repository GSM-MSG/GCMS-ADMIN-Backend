package com.example.msgadminapi.domain.entity.teacher;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Teacher {
    @Id
    private String email;

    private String password;

    private String name;
}
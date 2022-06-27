package com.example.msgadminapi.domain.entity.teacher;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "teacher")
public class Teacher {
    @Id
    private String userId;

    private String password;
}
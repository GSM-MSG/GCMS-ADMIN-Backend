package com.example.msgadminapi.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue()
    private String email;

    private String name;

    private int grade;

    private int class_;

    private int num;

    @Column( nullable = true )
    private String userImg;

    @Column( nullable = true )
    private String refeshToken;

    @Builder
    public User(String email, String name, int grade, int class_, int num, String userImg, String refeshToken) {
        this.email = email;
        this.name = name;
        this.grade = grade;
        this.class_ = class_;
        this.num = num;
        this.userImg = userImg;
        this.refeshToken = refeshToken;
    }
}
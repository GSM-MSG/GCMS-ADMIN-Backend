package com.example.msgadminapi.domain.entity.classregistration;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.user.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ClassRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterschool_id")
    private AfterSchool afterSchool;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_email")
    private User user;
}
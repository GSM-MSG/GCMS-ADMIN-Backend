package com.example.msgadminapi.domain.entity.classregistration;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.user.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "class_registration")
public class ClassRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterSchoolId", nullable = false)
    private AfterSchool afterSchool;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userEmail", nullable = false)
    private User user;
}

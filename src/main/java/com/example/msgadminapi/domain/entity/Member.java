package com.example.msgadminapi.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
}

package com.example.msgadminapi.domain.entity.club;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성해준다
@ToString
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private String bannerUrl;

    private String title;

    private String description;

    private String contact;

    private String teacher;

    private boolean isOpened;



}

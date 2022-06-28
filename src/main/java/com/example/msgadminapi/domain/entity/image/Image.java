package com.example.msgadminapi.domain.entity.image;

import com.example.msgadminapi.domain.entity.club.Club;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clubId")
    private Club club;

    private String url;
}

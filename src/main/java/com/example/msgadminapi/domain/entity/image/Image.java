package com.example.msgadminapi.domain.entity.image;

import com.example.msgadminapi.domain.entity.club.Club;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "club_id")
    private Club club;

    private String url;
}

package com.example.msgadminapi.domain.entity.realtedlink;

import com.example.msgadminapi.domain.entity.club.Club;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class RelatedLink{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "club_id")
    private Club club;
}

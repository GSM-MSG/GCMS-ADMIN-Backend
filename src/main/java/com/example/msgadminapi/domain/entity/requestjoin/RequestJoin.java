package com.example.msgadminapi.domain.entity.requestjoin;

import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.entity.user.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "requestJoin")
public class RequestJoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clubId")
    private Club club;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userEmail")
    private User user;
}

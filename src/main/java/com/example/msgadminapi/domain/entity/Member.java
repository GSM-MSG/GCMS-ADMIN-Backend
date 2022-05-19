package com.example.msgadminapi.domain.entity;

import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Member {
    @Id
    private Long id;

    private String scope;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_email")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Club club;

    public void mapping(User user){
        this.user=user;
        user.getMembers().add(this);
    }
}

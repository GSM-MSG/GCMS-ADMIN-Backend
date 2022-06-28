package com.example.msgadminapi.domain.entity.member;

import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.entity.member.enums.Scope;
import com.example.msgadminapi.domain.entity.user.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Scope scope;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userEmail")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clubId")
    private Club club;

    public void userMapping(User user){
        this.user=user;
        user.getMember().add(this);
    }

    public void clubMapping(Club club) {
        this.club = club;
        club.getMember().add(this);
    }

    public void changeScope(Scope scope){
        this.scope=scope;
    }

    public Member(){
        this.scope=Scope.MEMBER;
    }

}

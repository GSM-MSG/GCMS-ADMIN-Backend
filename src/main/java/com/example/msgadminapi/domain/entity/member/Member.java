package com.example.msgadminapi.domain.entity.member;

import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.entity.member.enums.Scope;
import com.example.msgadminapi.domain.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Scope scope;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_email")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "club_id")
    private Club club;

    public void userMapping(User user){
        this.user=user;
        user.getMembers().add(this);
    }

    public void clubMapping(Club club) {
        this.club = club;
        club.getMembers().add(this);
    }

    public void changeScope(Scope scope){
        this.scope=scope;
    }

    public Member(){
        this.scope=Scope.MEMBER;
    }

}

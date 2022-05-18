package com.example.msgadminapi.domain.entity;

import com.example.msgadminapi.domain.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    public void mapping(User user){
        this.user=user;
        user.getMembers().add(this);
    }
}

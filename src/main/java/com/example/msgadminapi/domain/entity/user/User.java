package com.example.msgadminapi.domain.entity.user;

import com.example.msgadminapi.domain.entity.member.Member;
import com.example.msgadminapi.domain.entity.requestjoin.RequestJoin;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users")

public class User {
    @Id
    @Column(name = "users_email")
    private String email;

    private String name;

    private int grade;

    private int class_;

    private int num;

    @Column( nullable = true )
    private String userImg;

    @Column( nullable = true )
    private String refreshToken;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Member> members = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<RequestJoin> requestJoins = new HashSet<>();

    // 회원 정보 수정
    public void update(int grade, int class_, int num) {
        this.grade = grade;
        this.class_ = class_;
        this.num = num;
    }
}

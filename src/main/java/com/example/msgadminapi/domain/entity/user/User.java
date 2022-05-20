package com.example.msgadminapi.domain.entity.user;

import com.example.msgadminapi.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private String email;

    private String name;

    private int grade;

    private int class_;

    private int num;

    @Column( nullable = true )
    private String userImg;

    @Column( nullable = true )
    private String refeshToken;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Member> members = new ArrayList<>();

    @Builder
    public User(String email, String name, int grade, int class_, int num, String userImg, String refeshToken) {
        this.email = email;
        this.name = name;
        this.grade = grade;
        this.class_ = class_;
        this.num = num;
        this.userImg = userImg;
        this.refeshToken = refeshToken;
    }

    // 회원 정보 수정
    public void update(int grade, int class_, int num) {
        this.grade = grade;
        this.class_ = class_;
        this.num = num;
    }
}

package com.example.msgadminapi.domain.entity.user;

import com.example.msgadminapi.domain.entity.classregistration.ClassRegistration;
import com.example.msgadminapi.domain.entity.member.Member;
import com.example.msgadminapi.domain.entity.requestjoin.RequestJoin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users") // 나중에 본 DB 연결 할때 지워야됨
public class User implements UserDetails {
    @Id
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
    private Set<Member> member = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<RequestJoin> requestJoin = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<ClassRegistration> classRegistration = new HashSet<>();

    // 회원 정보 수정
    public void update(int grade, int class_, int num) {
        this.grade = grade;
        this.class_ = class_;
        this.num = num;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

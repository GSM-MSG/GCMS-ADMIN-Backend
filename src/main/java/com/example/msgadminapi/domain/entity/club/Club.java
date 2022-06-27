package com.example.msgadminapi.domain.entity.club;

import com.example.msgadminapi.domain.entity.club.enums.Type;
import com.example.msgadminapi.domain.entity.image.Image;
import com.example.msgadminapi.domain.entity.member.Member;
import com.example.msgadminapi.domain.entity.requestjoin.RequestJoin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성해준다
@Table(name="club")
@ToString
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String bannerUrl;

    private String title;

    private String description;

    private String contact;

    private String teacher;

    @Column(columnDefinition = "TINYINT")
    private Boolean isOpened;

    private String notionLink;

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    private Set<Member> member = new HashSet<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    private Set<Image> activityUrls = new HashSet<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    private Set<RequestJoin> requestJoin = new HashSet<>();

    public void titleModify(String title) {
        this.title = title;
    }

    public void isClubFinishOpen(Boolean isClosure) {
        this.isOpened = isClosure;
    }
}

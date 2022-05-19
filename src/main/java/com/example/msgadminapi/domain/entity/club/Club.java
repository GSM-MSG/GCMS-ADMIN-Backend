package com.example.msgadminapi.domain.entity.club;

import com.example.msgadminapi.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Entity
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성해준다
@ToString
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private String bannerUrl;

    private String title;

    private String description;

    private String contact;

    private String teacher;

    @Column(columnDefinition = "TINYINT")
    private Boolean isOpened;

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    private List<Member> members = new ArrayList<>();

    public void titleModify(String title) {
        this.title = title;
    }

    public void isClubFinishOpen(Boolean isClosure) {
        this.isOpened = isClosure;
    }
}

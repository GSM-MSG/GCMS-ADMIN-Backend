package com.example.msgadminapi.domain.entity.afterschool;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "afterSchoolId")
    private AfterSchool afterSchool;

    private Integer grade;

    public void mapping(AfterSchool afterSchool){
        this.afterSchool=afterSchool;
    }
}

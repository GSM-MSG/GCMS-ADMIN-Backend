package com.example.msgadminapi.domain.entity.afterschool;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "day_of_week")
public class DayOfWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "afterSchoolId")
    private AfterSchool afterSchool;

    private String dayOfWeek;

    public void mapping(AfterSchool afterSchool){
        this.afterSchool=afterSchool;
    }
}

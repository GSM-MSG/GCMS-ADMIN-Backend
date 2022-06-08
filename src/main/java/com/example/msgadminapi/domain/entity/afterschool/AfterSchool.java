package com.example.msgadminapi.domain.entity.afterschool;


import com.example.msgadminapi.domain.entity.afterschool.enums.Season;
import com.example.msgadminapi.domain.entity.classregistration.ClassRegistration;
import com.example.msgadminapi.domain.entity.member.Member;
import com.example.msgadminapi.dto.request.AfterSchoolModifyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class AfterSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long personnel;

    @OneToMany(mappedBy = "afterSchool")
    private List<Grade> grade;

    @OneToMany(mappedBy = "afterSchool")
    private List<DayOfWeek> dayOfWeek;

    private String teacher;

    @Column(columnDefinition = "TINYINT")
    private Boolean canDuplicate;

    @Enumerated(EnumType.STRING)
    private Season season;

    private Long yearOf;

    @Column(columnDefinition = "TINYINT")
    private Boolean isOpened;

    @OneToMany(mappedBy = "afterSchool", fetch = FetchType.EAGER)
    private Set<ClassRegistration> classRegistration = new HashSet<>();

    public void update(AfterSchoolModifyDto afterSchoolDto){
        this.title = afterSchoolDto.getTitle();
        this.teacher = afterSchoolDto.getTeacher();
        this.canDuplicate = afterSchoolDto.getCanDuplicate();
        this.season = afterSchoolDto.getSeason();
        if(this.grade.size() > afterSchoolDto.getGrade().size()){
            for(int i=0;i<afterSchoolDto.getGrade().size();i++){
                this.grade.get(i).setGrade(afterSchoolDto.getGrade().get(i));
            }
            for(int i=afterSchoolDto.getGrade().size();i<this.grade.size();i++){
                this.grade.remove(i);
            }
        }
        else if(this.grade.size() < afterSchoolDto.getGrade().size()){
            for(int i=0;i<this.getGrade().size();i++){
                this.grade.get(i).setGrade(afterSchoolDto.getGrade().get(i));
            }
            for(int i=this.getGrade().size();i<afterSchoolDto.getGrade().size();i++){
                this.grade.add(Grade.builder().grade(afterSchoolDto.getGrade().get(i)).afterSchool(this).build());
            }
        }
        else{
            for(int i=0;i<this.getGrade().size();i++){
                this.grade.get(i).setGrade(afterSchoolDto.getGrade().get(i));
            }
        }
    }
}

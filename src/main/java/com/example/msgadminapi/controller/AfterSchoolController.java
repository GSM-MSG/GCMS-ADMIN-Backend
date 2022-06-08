package com.example.msgadminapi.controller;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.member.Member;
import com.example.msgadminapi.dto.request.AfterSchoolDto;
import com.example.msgadminapi.dto.request.AfterSchoolModifyDto;
import com.example.msgadminapi.dto.response.StatisticsResponseDto;
import com.example.msgadminapi.response.ResponseService;
import com.example.msgadminapi.response.result.CommonResultResponse;
import com.example.msgadminapi.service.AfterSchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/afterSchool")
@RequiredArgsConstructor
public class AfterSchoolController {

    private final AfterSchoolService afterSchoolService;
    private final ResponseService responseService;

    @PostMapping
    public CommonResultResponse create(@RequestBody AfterSchoolDto afterSchoolDto){
        afterSchoolService.createAfterSchool(afterSchoolDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{afterSchoolIdx}")
    public CommonResultResponse delete(@PathVariable Long afterSchoolIdx){
        afterSchoolService.deleteAfterSchool(afterSchoolIdx);
        return responseService.getSuccessResult();
    }

    @GetMapping("/statistics")
    public List<StatisticsResponseDto> findStatistics(){
        return afterSchoolService.getStatistics();
    }

    @GetMapping
    public List<AfterSchool> findAll(){
        return afterSchoolService.findAll();
    }

    @PutMapping("/{afterSchoolIdx}")
    public CommonResultResponse updateAfterSchool(@PathVariable Long afterSchoolIdx, @RequestBody AfterSchoolModifyDto afterSchoolDto){
        afterSchoolService.updateAfterSchool(afterSchoolIdx, afterSchoolDto);
         return responseService.getSuccessResult();
    }

    @GetMapping("/members/{afterSchoolIdx}")
    public List<Member> findMembers(@PathVariable Long afterSchoolIdx){
        return afterSchoolService.findMemberByAfterSchool(afterSchoolIdx);
    }


}

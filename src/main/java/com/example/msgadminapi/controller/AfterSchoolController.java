package com.example.msgadminapi.controller;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.afterschool.enums.Season;
import com.example.msgadminapi.domain.entity.member.Member;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.dto.request.AfterSchoolDto;
import com.example.msgadminapi.dto.request.AfterSchoolModifyDto;
import com.example.msgadminapi.dto.response.StatisticsResponseDto;
import com.example.msgadminapi.response.ResponseService;
import com.example.msgadminapi.response.result.CommonResultResponse;
import com.example.msgadminapi.service.AfterSchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/afterSchool")
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/users/{afterSchoolIdx}")
    public List<User> findUser(@PathVariable Long afterSchoolIdx){
        return afterSchoolService.findUserByAfterSchool(afterSchoolIdx);
    }

    @PutMapping("/close/all")
    public CommonResultResponse closeAllAfterSchool(@RequestParam Season season, @RequestParam Long year) {
        afterSchoolService.closeAllAfterSchool(season, year);
        return responseService.getSuccessResult();
    }

    @PutMapping("/close/{afterSchoolIdx}")
    public CommonResultResponse closeAfterSchool(@PathVariable Long afterSchoolIdx, @RequestParam Season season, @RequestParam Long year) {
        System.out.println("1");
        afterSchoolService.closeAfterSchool(afterSchoolIdx, season, year);
        return responseService.getSuccessResult();
    }

    @PutMapping("/open/all")
    public CommonResultResponse openAllAfterSchool(@RequestParam Season season, @RequestParam Long year) {
        afterSchoolService.openAllAfterSchool(season, year);
        return responseService.getSuccessResult();
    }

    @PutMapping("/open/{afterSchoolIdx}")
    public CommonResultResponse openAfterSchool(@PathVariable Long afterSchoolIdx, @RequestParam Season season, @RequestParam Long year) {
        afterSchoolService.openAfterSchool(afterSchoolIdx, season, year);
        return responseService.getSuccessResult();
    }

}

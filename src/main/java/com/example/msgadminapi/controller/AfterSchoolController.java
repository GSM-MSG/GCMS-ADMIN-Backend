package com.example.msgadminapi.controller;

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool;
import com.example.msgadminapi.domain.entity.afterschool.enums.Season;
import com.example.msgadminapi.domain.entity.classregistration.ClassRegistration;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.dto.request.AfterSchoolDto;
import com.example.msgadminapi.dto.request.AfterSchoolModifyDto;
import com.example.msgadminapi.dto.response.AfterSchoolFindResponseDto;
import com.example.msgadminapi.dto.response.ClassRegistrationUserResDto;
import com.example.msgadminapi.dto.response.StatResponseDto;
import com.example.msgadminapi.dto.request.UserEmailDto;
import com.example.msgadminapi.dto.response.UserResponseDto;
import com.example.msgadminapi.response.ResponseService;
import com.example.msgadminapi.response.result.CommonResultResponse;
import com.example.msgadminapi.service.AfterSchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/afterschool")
@RequiredArgsConstructor
public class AfterSchoolController {

    private final AfterSchoolService afterSchoolService;
    private final ResponseService responseService;

    @PostMapping
    public AfterSchoolFindResponseDto create(@RequestBody AfterSchoolDto afterSchoolDto){
        return afterSchoolService.createAfterSchool(afterSchoolDto);
    }

    @DeleteMapping("/{afterSchoolIdx}")
    public CommonResultResponse delete(@PathVariable Integer afterSchoolIdx){
        afterSchoolService.deleteAfterSchool(afterSchoolIdx);
        return responseService.getSuccessResult();
    }

    @GetMapping("/statistics")
    public StatResponseDto findStatistics(){
        return afterSchoolService.getStatistics();
    }

    @GetMapping
    public List<AfterSchoolFindResponseDto> findAll(){
        return afterSchoolService.findAll();
    }

    @PutMapping("/{afterSchoolIdx}")
    public CommonResultResponse updateAfterSchool(@PathVariable Integer afterSchoolIdx, @RequestBody AfterSchoolModifyDto afterSchoolDto){
        afterSchoolService.updateAfterSchool(afterSchoolIdx, afterSchoolDto);
         return responseService.getSuccessResult();
    }

    @GetMapping("/users/{afterSchoolIdx}")
    public List<ClassRegistrationUserResDto> findUser(@PathVariable Integer afterSchoolIdx){
        return afterSchoolService.findUserByAfterSchool(afterSchoolIdx);
    }

    @PutMapping("/close/all")
    public CommonResultResponse closeAllAfterSchool(@RequestParam Season season, @RequestParam Integer year) {
        afterSchoolService.closeAllAfterSchool(season, year);
        return responseService.getSuccessResult();
    }

    @PutMapping("/close/{afterSchoolIdx}")
    public CommonResultResponse closeAfterSchool(@PathVariable Integer afterSchoolIdx) {
        afterSchoolService.closeAfterSchool(afterSchoolIdx);
        return responseService.getSuccessResult();
    }

    @PutMapping("/open/all")
    public CommonResultResponse openAllAfterSchool(@RequestParam Season season, @RequestParam Integer year) {
        afterSchoolService.openAllAfterSchool(season, year);
        return responseService.getSuccessResult();
    }

    @PutMapping("/open/{afterSchoolIdx}")
    public CommonResultResponse openAfterSchool(@PathVariable Integer afterSchoolIdx) {
        afterSchoolService.openAfterSchool(afterSchoolIdx);
        return responseService.getSuccessResult();
    }

    @PatchMapping("/users/{afterSchoolIdx}")
    public CommonResultResponse deleteApplyMember(@PathVariable Integer afterSchoolIdx, @RequestBody UserEmailDto userEmailDto){
        afterSchoolService.deleteApplyMember(afterSchoolIdx, userEmailDto.getEmail());
        return responseService.getSuccessResult();
    }
}

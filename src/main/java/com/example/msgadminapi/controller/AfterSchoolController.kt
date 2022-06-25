package com.example.msgadminapi.controller

import com.example.msgadminapi.domain.entity.afterschool.AfterSchool
import com.example.msgadminapi.domain.entity.afterschool.enums.Season
import com.example.msgadminapi.domain.entity.user.User
import com.example.msgadminapi.dto.request.AfterSchoolDto
import com.example.msgadminapi.dto.request.AfterSchoolModifyDto
import com.example.msgadminapi.dto.response.StatisticsResponseDto
import com.example.msgadminapi.response.ResponseService
import com.example.msgadminapi.response.result.CommonResultResponse
import com.example.msgadminapi.service.AfterSchoolService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/afterSchool")
class AfterSchoolController(
    private val afterSchoolService: AfterSchoolService,
    private val responseService: ResponseService
) {
    @PostMapping
    fun create(@RequestBody afterSchoolDto: AfterSchoolDto?): CommonResultResponse {
        afterSchoolService.createAfterSchool(afterSchoolDto)
        return responseService.successResult
    }

    @DeleteMapping("/{afterSchoolIdx}")
    fun delete(@PathVariable afterSchoolIdx: Long?): CommonResultResponse {
        afterSchoolService.deleteAfterSchool(afterSchoolIdx)
        return responseService.successResult
    }

    @GetMapping("/statistics")
    fun findStatistics(): List<StatisticsResponseDto> {
        return afterSchoolService.statistics
    }

    @GetMapping
    fun findAll(): List<AfterSchool> {
        return afterSchoolService.findAll()
    }

    @PutMapping("/{afterSchoolIdx}")
    fun updateAfterSchool(
        @PathVariable afterSchoolIdx: Long?,
        @RequestBody afterSchoolDto: AfterSchoolModifyDto?
    ): CommonResultResponse {
        afterSchoolService.updateAfterSchool(afterSchoolIdx, afterSchoolDto)
        return responseService.successResult
    }

    @GetMapping("/users/{afterSchoolIdx}")
    fun findUser(@PathVariable afterSchoolIdx: Long?): List<User> {
        return afterSchoolService.findUserByAfterSchool(afterSchoolIdx)
    }

    @PutMapping("/close/all")
    fun closeAllAfterSchool(@RequestParam season: Season?, @RequestParam year: Long?): CommonResultResponse {
        afterSchoolService.closeAllAfterSchool(season, year)
        return responseService.successResult
    }

    @PutMapping("/close/{afterSchoolIdx}")
    fun closeAfterSchool(
        @PathVariable afterSchoolIdx: Long?,
        @RequestParam season: Season?,
        @RequestParam year: Long?
    ): CommonResultResponse {
        afterSchoolService.closeAfterSchool(afterSchoolIdx, season, year)
        return responseService.successResult
    }

    @PutMapping("/open/all")
    fun openAllAfterSchool(@RequestParam season: Season?, @RequestParam year: Long?): CommonResultResponse {
        afterSchoolService.openAllAfterSchool(season, year)
        return responseService.successResult
    }

    @PutMapping("/open/{afterSchoolIdx}")
    fun openAfterSchool(
        @PathVariable afterSchoolIdx: Long?,
        @RequestParam season: Season?,
        @RequestParam year: Long?
    ): CommonResultResponse {
        afterSchoolService.openAfterSchool(afterSchoolIdx, season, year)
        return responseService.successResult
    }
}
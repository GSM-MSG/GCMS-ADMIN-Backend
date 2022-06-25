package com.example.msgadminapi.controller

import com.example.msgadminapi.dto.request.ClubTitleModifyRequest
import com.example.msgadminapi.dto.response.ClubResponseDto
import com.example.msgadminapi.response.ResponseService
import com.example.msgadminapi.response.result.CommonResultResponse
import com.example.msgadminapi.service.ClubService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
class ClubController(
    private val clubService: ClubService,
    private val responseService: ResponseService
) {
    @GetMapping
    fun clubFindAll(): List<ClubResponseDto> {
        return clubService.findAll()
    }

    @GetMapping("/search")
    fun search(@RequestParam("title") title: String?): List<ClubResponseDto> {
        return clubService.search(title)
    }

    @PatchMapping("/title")
    fun titleModify(@RequestParam request: ClubTitleModifyRequest?): CommonResultResponse {
        clubService.clubTitleModify(request)
        return responseService.successResult
    }

    @DeleteMapping("{clubIdx}")
    fun clubDelete(@PathVariable clubIdx: Long?): CommonResultResponse {
        clubService.clubDelete(clubIdx)
        return responseService.successResult
    }

    @PatchMapping("{clubIdx}/end")
    fun clubFinishOpen(@PathVariable clubIdx: Long?): CommonResultResponse {
        clubService.clubClose(clubIdx)
        return responseService.successResult
    }
}
package com.example.msgadminapi.controller

import com.example.msgadminapi.response.ResponseService
import com.example.msgadminapi.response.result.CommonResultResponse
import com.example.msgadminapi.service.MemberService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService,
    private val responseService: ResponseService
) {
    @PostMapping("/{email}/{clubIdx}")
    fun insertMember(@PathVariable email: String?, @PathVariable clubIdx: Long?): CommonResultResponse {
        memberService.insertMember(email, clubIdx)
        return responseService.successResult
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long?): CommonResultResponse {
        memberService.deleteMember(id)
        return responseService.successResult
    }

    @PatchMapping("/move/{memberIdx}/{clubIdx}")
    fun moveMember(@PathVariable memberIdx: Long?, @PathVariable clubIdx: Long?): CommonResultResponse {
        memberService.moveMember(memberIdx, clubIdx)
        return responseService.successResult
    }

    @PatchMapping("/club/manager/{memberIdx}")
    fun changeManager(@PathVariable memberIdx: Long?): CommonResultResponse {
        memberService.changeManager(memberIdx)
        return responseService.successResult
    }
}
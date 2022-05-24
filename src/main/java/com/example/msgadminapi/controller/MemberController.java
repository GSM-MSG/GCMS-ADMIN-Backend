package com.example.msgadminapi.controller;

import com.example.msgadminapi.domain.entity.member.enums.Scope;
import com.example.msgadminapi.response.ResponseService;
import com.example.msgadminapi.response.result.CommonResultResponse;
import com.example.msgadminapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;

    @PostMapping("/{email}/{clubIdx}")
    public CommonResultResponse insertMember(@PathVariable String email, @PathVariable Long clubIdx) {
        memberService.insertMember(email, clubIdx);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{id}")
    public CommonResultResponse deleteMember(@PathVariable Long id) throws Exception {
        memberService.deleteMember(id);
        return responseService.getSuccessResult();
    }

    @PatchMapping("/move/{memberIdx}/{clubIdx}")
    public CommonResultResponse moveMember(@PathVariable Long memberIdx, @PathVariable Long clubIdx) {
        memberService.moveMember(memberIdx, clubIdx);
        return responseService.getSuccessResult();
    }

    @PatchMapping("/club/manager/{memberIdx}/{clubIdx}")
    public CommonResultResponse changeManager(@PathVariable Long memberIdx, @PathVariable Long clubIdx) {
        memberService.changeManager(memberIdx, clubIdx);
        return responseService.getSuccessResult();
    }

}

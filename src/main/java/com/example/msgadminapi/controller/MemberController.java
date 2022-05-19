package com.example.msgadminapi.controller;

import com.example.msgadminapi.domain.entity.Member;
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

    @PostMapping("/{email}")
    public CommonResultResponse insert(@PathVariable String email, @RequestBody Member member) {
        memberService.insertMember(email, member);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/{id}")
    public CommonResultResponse deleteMember(@PathVariable Long id) throws Exception {
        memberService.deleteMember(id);
        return responseService.getSuccessResult();
    }

}

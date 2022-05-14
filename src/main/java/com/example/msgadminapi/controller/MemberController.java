package com.example.msgadminapi.controller;

import com.example.msgadminapi.domain.entity.Member;
import com.example.msgadminapi.response.ResponseService;
import com.example.msgadminapi.response.result.CommonResultResponse;
import com.example.msgadminapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;

    @PostMapping("/{email}")
    public CommonResultResponse insert(@PathVariable String email, @RequestBody Member member) {
        memberService.insert(email, member);
        return responseService.getSuccessResult();
    }

}
package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.Member;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.domain.repository.MemberRepository;
import com.example.msgadminapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    public void insert(String email, Member member){
        User byEmail = userRepository.findByEmail(email);
        member.mapping(byEmail);
        memberRepository.save(member);
    }
}

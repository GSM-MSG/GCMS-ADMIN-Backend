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

    public void insertMember(String email, Member member){
        User byEmail = userRepository.findByEmail(email);
        member.mapping(byEmail);
        memberRepository.save(member);
    }

    public void deleteMember(Long id) throws Exception{
        Member member = memberRepository.findById(id)
                        .orElseThrow(() -> new Exception("존재하지 않는 멤버 엔티티 튜플입니다."));
        memberRepository.delete(member);
    }
}

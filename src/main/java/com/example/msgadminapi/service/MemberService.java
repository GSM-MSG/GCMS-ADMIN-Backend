package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.Member;
import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.domain.repository.ClubRepository;
import com.example.msgadminapi.domain.repository.MemberRepository;
import com.example.msgadminapi.domain.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;


    public void insertMember(String email, Long clubIdx) {
        System.out.println("club email" + email);
        Member member = new Member();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("오류1"));
        System.out.println("1" + user);
        Club club = clubRepository.findById(clubIdx).orElseThrow(()-> new RuntimeException("오류"));
        System.out.println("2" + club);
        member.userMapping(user);
        member.clubMapping(club);
        memberRepository.save(member);
    }

    public void deleteMember(Long id) throws Exception{
        Member member = memberRepository.findById(id)
                        .orElseThrow(() -> new Exception("존재하지 않는 멤버 엔티티 튜플입니다."));
        memberRepository.delete(member);
    }
}

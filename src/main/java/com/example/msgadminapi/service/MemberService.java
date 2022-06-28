package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.member.Member;
import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.entity.member.enums.Scope;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.domain.repository.ClubRepository;
import com.example.msgadminapi.domain.repository.MemberRepository;
import com.example.msgadminapi.domain.repository.UserRepository;
import com.example.msgadminapi.exception.exception.ClubNotFoundException;
import com.example.msgadminapi.exception.exception.MemberNotFoundException;
import com.example.msgadminapi.exception.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;

    public void insertMember(String email, Integer clubIdx) {
        Member member = new Member();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
        Club club = clubRepository.findById(clubIdx)
                .orElseThrow(()-> new ClubNotFoundException());
        member.userMapping(user);
        member.clubMapping(club);
        memberRepository.save(member);
    }

    public void deleteMember(Integer id) {
        Member member = memberRepository.findById(id)
                        .orElseThrow(() -> new MemberNotFoundException());
        memberRepository.delete(member);
    }

    public void moveMember(Integer memberIdx, Integer clubIdx) {
        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new MemberNotFoundException());
        Club club = clubRepository.findById(clubIdx)
                .orElseThrow(() -> new ClubNotFoundException());
        member.getClub().getMember().remove(member);
        member.clubMapping(club);
    }

    @Transactional
    public void changeManager(Integer memberIdx) {
        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new MemberNotFoundException());
        Set<Member> members = member.getClub().getMember();
        for(Member m : members) {
            if(m.getScope() == Scope.HEAD) {
                m.changeScope(Scope.MEMBER);
            }
        }
        member.changeScope(Scope.HEAD);
    }
}

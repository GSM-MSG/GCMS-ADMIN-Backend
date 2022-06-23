package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.member.Member;
import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.entity.member.enums.Scope;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.domain.repository.ClubRepository;
import com.example.msgadminapi.domain.repository.MemberRepository;
import com.example.msgadminapi.domain.repository.UserRepository;
import com.example.msgadminapi.exception.ErrorCode;
import com.example.msgadminapi.exception.exception.ClubNotFindException;
import com.example.msgadminapi.exception.exception.MemberNotFindException;
import com.example.msgadminapi.exception.exception.UserNotFindException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;

    public void insertMember(String email, Long clubIdx) {
        Member member = new Member();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFindException());
        Club club = clubRepository.findById(clubIdx)
                .orElseThrow(()-> new ClubNotFindException());
        member.userMapping(user);
        member.clubMapping(club);
        memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                        .orElseThrow(() -> new MemberNotFindException());
        memberRepository.delete(member);
    }

    public void moveMember(Long memberIdx, Long clubIdx) {
        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new MemberNotFindException());
        Club club = clubRepository.findById(clubIdx)
                .orElseThrow(() -> new ClubNotFindException());
        member.getClub().getMember().remove(member);
        member.clubMapping(club);
    }

    @Transactional
    public void changeManager(Long memberIdx) {
        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new MemberNotFindException());
        Set<Member> members = member.getClub().getMember();
        for(Member m : members) {
            if(m.getScope() == Scope.HEAD) {
                m.changeScope(Scope.MEMBER);
            }
        }
        member.changeScope(Scope.HEAD);
    }
}

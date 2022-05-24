package com.example.msgadminapi.service;

import com.example.msgadminapi.domain.entity.member.Member;
import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.entity.member.enums.Scope;
import com.example.msgadminapi.domain.entity.user.User;
import com.example.msgadminapi.domain.repository.ClubRepository;
import com.example.msgadminapi.domain.repository.MemberRepository;
import com.example.msgadminapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
        member.userMapping(user);
        member.clubMapping(club);
        memberRepository.save(member);
    }

    public void deleteMember(Long id) throws Exception{
        Member member = memberRepository.findById(id)
                        .orElseThrow(() -> new Exception("존재하지 않는 멤버 엔티티 튜플입니다."));
        memberRepository.delete(member);
    }

    public void moveMember(Long memberIdx, Long clubIdx) {
        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new RuntimeException("런타임 오류"));
        Club club = clubRepository.findById(clubIdx)
                .orElseThrow(() -> new RuntimeException("런타임 오류2"));
        member.getClub().getMembers().remove(member);
        member.changeClub(club);
        club.getMembers().add(member);
        memberRepository.save(member);
    }

    @Transactional
    public void changeManager(Long memberIdx, Long clubIdx) {
        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new RuntimeException("멤버 Change 함수에서 불러오다 오류"));
        List<Member> members = member.getClub().getMembers();
        for(Member m : members) {
            if(m.getScope() == Scope.HEAD) {
                m.changeScope(Scope.MEMBER);
            }
        }
        member.changeScope(Scope.HEAD);
    }
}

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
                .orElseThrow(() -> new UserNotFindException("동아리 구성원을 강제 추가하려는 과정중에 유저를 찾지 못했습니다.", ErrorCode.USER_NOT_FIND));
        Club club = clubRepository.findById(clubIdx)
                .orElseThrow(()-> new ClubNotFindException("동아리 구성원을 강제 추가하려는 과정중에 동아리를 찾지 못했습니다.", ErrorCode.CLUB_NOT_FIND));
        member.userMapping(user);
        member.clubMapping(club);
        memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                        .orElseThrow(() -> new MemberNotFindException("동아리 구성원을 강제로 지우는 과정중에 구성원을 찾지 못했습니다.", ErrorCode.MEMBER_NOT_FIND));
        memberRepository.delete(member);
    }

    public void moveMember(Long memberIdx, Long clubIdx) {
        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new MemberNotFindException("동아리 구성원을 강제로 이동하는 과정중에 구성원을 찾지 못했습니다.", ErrorCode.MEMBER_NOT_FIND));
        Club club = clubRepository.findById(clubIdx)
                .orElseThrow(() -> new ClubNotFindException("동아리 구성원을 강제로 이동하는 과정중에 동아리를 찾지 못했습니다.", ErrorCode.CLUB_NOT_FIND));
        member.getClub().getMembers().remove(member);
        member.clubMapping(club);
    }

    @Transactional
    public void changeManager(Long memberIdx) {
        Member member = memberRepository.findById(memberIdx)
                .orElseThrow(() -> new MemberNotFindException("동아리 부장을 교체하려는 과정중에 구성원을 찾지 못했습니다.", ErrorCode.MEMBER_NOT_FIND));
        Set<Member> members = member.getClub().getMembers();
        for(Member m : members) {
            if(m.getScope() == Scope.HEAD) {
                m.changeScope(Scope.MEMBER);
            }
        }
        member.changeScope(Scope.HEAD);
    }
}

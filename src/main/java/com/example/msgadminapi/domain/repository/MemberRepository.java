package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.club.Club;
import com.example.msgadminapi.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}

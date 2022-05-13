package com.example.msgadminapi.domain.repository;

import com.example.msgadminapi.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}

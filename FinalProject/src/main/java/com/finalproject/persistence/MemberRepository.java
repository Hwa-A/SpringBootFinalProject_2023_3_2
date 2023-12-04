package com.finalproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.domain.Member;


public interface MemberRepository extends JpaRepository<Member, String> {
	boolean existsByEmail(String email);
	boolean existsByUname(String uname);
	Member findByEmail(String email);
	
}

package com.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finalproject.domain.PrincipalDetails;
import com.finalproject.domain.Member;
import com.finalproject.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

// UserDetailsService란? Spring Security에서 사용자 정보를 가져오는 인터페이스
// 					   : Security에서 사용할 PrincipalDetails를 반환
// 						성공적으로 return되면 자동으로 session 생성
@RequiredArgsConstructor	// final이나 @NonNull인 필드 값만 매개변수로 받는 생성자 생성 
@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private final MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// loadUserByUsername()의 인자는 로그인할 때 id를 의미
		// View에서 form으로 보낼 때 반드시 name=userName 처럼 설정
		System.out.println("email");
		Member member = memberRepo.findByEmail(email);
		if(member == null) {
			throw new UsernameNotFoundException("PrincipalDetailsService: user객체를 찾을 수 없습니다.");
		}else {
			return new PrincipalDetails(member);
		}
	}

}

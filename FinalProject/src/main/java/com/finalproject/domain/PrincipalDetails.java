package com.finalproject.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.finalproject.domain.Member;

// UserDetails란? Spring Security에서 제공하는 사용자의 정보, 권한을 담는 인터페이스
//					 / 기본적으로 username과 password만 받아오기 때문에 따로 커스텀이 필요
// PrincipalDetails 클래스(개발자 구현): UserDetails의 구현체
//						: 인증된 주체(Principal)의 상세 정보 관리

public class PrincipalDetails implements UserDetails {
	
	private Member member;	// DB에서 조회한 사용자 정보를 담는 클래스
	
	// 생성자
	public PrincipalDetails(Member member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// : GrantedAuthority의 컬렉션을 반환 /  사용자가 가진 모든 권한 목록 리턴
		// 사용자의 특정 권한 소유 확인, 권한에 따른 접근 제어 등에 사용
		
		// ? : 와일드 카드 타입 = 불특정한 타입
		//    GrantedAuthority 또는 GrantedAuthority의 하위 타입 중 어떤 타입이든 올 수 있음을 의미
		// GrantedAuthority: 사용자에게 부여된 특정 권한을 나타내는 클래스 ex.ROLE_ADMIN, ROLE_USER
		
		return Collections.singletonList(new SimpleGrantedAuthority(member.getRole()));
		// Collections.singletonList(): 인자로 준 객체 하나만을 원소로 하는 불변 리스트 생성
		// SimpleGrantedAuthority: GrantedAuthority 인터페이스의 간단한 구현체
		//						  생성자에 권한을 나타낸 문자열 전달 -> 해당 문자열(ex.USER)을 가진 GrantedAuthority 객체를 생성
	}

	@Override
	public String getPassword() {
		// : 사용자 password 반환
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// : 사용자 식별 정보(PK값)를 반환 => 반환된 정보로 DB에서 사용자 정보를 획득
		// 획득된 정보로 PrincipalDetails 객체 생성
		// email로 로그인하는 경우, email을 반환값으로 설정
		return member.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// : 사용자 계정 만료 여부 반환(true: 만료X)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// : 사용자 계정이 잠겼는지 여부 반환(true: 잠김X)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// : 사용자의 인증 정보(주로 비밀번호)가 만료되지 않았는지 여부 반환(tue: 만료X)
		return true;
	}

	@Override
	public boolean isEnabled() {
		// : 사용자 계정 활성화 여부 반환(true: 활성화)
		return true;
	}
	
	// 회원 닉네임을 가져옴
	public String getUname() {
		return member.getUname();
	}
	
	// 회원 이메일을 가져옴
	public String getEmail() {
		return member.getEmail();
	}

}

/*
 * Security를 이용한 사용자 인증 과정
 * 
 * 1. 사용자가 로그인 -> 입력한 정보(id, password 등)를 담은 Authentication 객체 생성
 * 2. 생성된 Authentication 객체를 AuthenticationManager가 인증 처리 및 검증
 * 	  (내부적으로 AuthenticationProvider가 위임받아 처리)
 * 	  -> 검증 과정 중 UserDetailsService의 loadUserByUsername()이 호출됨
 * 	  +) loadUserByUsername(): PrincipalDetails 객체 생성 및 반환
 * 3. 인증 성공 시, Authentication 객체(PrincipalDetails 객체가 담긴 상태)가 SecurityContextHolder를 통해 
 * 	  SecurityContext에 저장 -> SecurityContext가 session에 저장
 * 	 
 * +) SecurityContext: Authentication 객체가 저장되는 보관소
 * +) SecurityContextHolder: SecurityContext를 감싸는 객체 
 * SecurityContextHolder.clearContext(): 기존 SecurityContext 정보 초기화
 * 
 */
 
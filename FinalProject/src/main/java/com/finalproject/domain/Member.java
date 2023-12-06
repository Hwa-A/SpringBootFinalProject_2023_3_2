package com.finalproject.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data	
@NoArgsConstructor	// 매개변수가 없는 기본 생성자 생성 ex. User user = new User()
@AllArgsConstructor	// 해당 클래스의 모든 필드를 매개변수로 받는 생성자 생성 ex. User user = new User(모든 필드 값 / null인 경우 직접 null 명시, 생략X => X시, error)
// @RequiredArgsConstructor	// final이나 @NonNull인 필드 값만 매개변수로 받는 생성자 생성 
@Builder	// 객체 생성 시, 필드에 값을 순서 없이 줄 수 있도록 도와줌
@ToString(exclude = "questionList")
@Entity
public class Member {	
	@Id
	@Column(nullable = false)	// : Null 허용X
	private String email;	// 회원 이메일(PK)
	private String uname;		// 회원 닉네임
	private String password;	// 회원 비밀번호
	@Column(columnDefinition = "VARCHAR(5) default 'USER'")
	private String role;	// 회원 권한(default: USER)

	// mappedBy 속성: 양방향 연관관계에서 연관관계의 주인이 아님을 설정
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)	// 일대다 / 연관된 엔티티도 같이 삭제
	private List<Question> questionList = new ArrayList<Question>();
}
/*
 * 연관관계의 주인은 데이터베이스의 연관관계와 매핑되 외래키를 관리(등록,수정,삭제)가능 
 * 주인이 아닌 쪽은 읽기만 가능
 */


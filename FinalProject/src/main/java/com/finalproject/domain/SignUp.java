package com.finalproject.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 가입 클래스
@Data
@NoArgsConstructor	// 기본 생성자 생성
public class SignUp {
	@NotBlank		// @NotBlank: null, "", "   " 모두 허용X
	@Email			// 이메일 형식 가지도록
	private String email;	// 회원 이메일(PK)
	
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]{2,10}$", message = "닉네임은 공백없이 특수문자를 제외한 2~10글자를 입력해주세요")
	private String uname;		// 회원 닉네임
	
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9\\s]).{8,16}", message = "비밀번호는 공백없이 영문(대소문자), 숫자, 특수문자를 최소 1글자씩 포함해 8~16글자를 입력해주세요")
	private String password;	// 회원 비밀번호
	private String passwordCheck;	// 비밀번호 확인
	
	// 암호화된 비밀번호를 가진 User 객체 반환
	public Member toEntity(String encodedPassword) {
		return Member.builder()
				.email(this.email)
				.uname(this.uname)
				.password(encodedPassword)
				.role("USER")
				.build();
	}
}

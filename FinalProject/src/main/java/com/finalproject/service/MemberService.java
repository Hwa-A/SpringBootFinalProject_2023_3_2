package com.finalproject.service;

import com.finalproject.domain.SignUp;

public interface MemberService {
	public boolean checkEmailDuplicate(String email);	// email 중복 확인(DB 데이터와)
	public boolean checkUnameDuplicate(String uname);	// uname 중복 확인(DB 데이터와)
	public void insertSignUp(SignUp signUp);		// 회원가입 성공하면 User 객체 DB에 저장
}

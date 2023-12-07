package com.finalproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalproject.domain.SignUp;
import com.finalproject.domain.Member;
import com.finalproject.persistence.MemberRepository;
import com.finalproject.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override	// email 중복 확인(DB 데이터와)
	public boolean checkEmailDuplicate(String email) {	
		
		return memberRepo.existsByEmail(email);	// DB에 존재하면 true / 없으면 false
	}

	@Override	// uname 중복 확인(DB 데이터와)
	public boolean checkUnameDuplicate(String uname) {	
		
		return memberRepo.existsByUname(uname);	// DB에 존재하면 true / 없으면 false
	}

	@Override	// 회원가입 성공하면 User 객체 DB에 저장
	public void insertSignUp(SignUp signUp) {	
		String encodedPassword = passwordEncoder.encode(signUp.getPassword());	// 패스워드 암호화
		Member member = signUp.toEntity(encodedPassword);	// 암호화된 비밀번호를 가진 User 객체 반환
		
		memberRepo.save(member);	// DB에 User 객체 저장
	}

	@Override	// 회원 탈퇴
	public void deleteMyAccount(String email) {
		memberRepo.deleteById(email);
		
	}
}

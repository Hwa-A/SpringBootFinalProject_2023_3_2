package com.finalproject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalproject.domain.SignUp;
import com.finalproject.service.MemberService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SignUpController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/signUp")
	public String signUpForm() {
		return "signUp";
	}
	
	// 유효성 검사 및 중복(이메일, 닉네임) 검사
	@PostMapping("/signUp")
	public String signUp(@Valid @ModelAttribute("signUp") SignUp signUp, BindingResult bindingResult) {
		
		// 유효성 검사에 에러가 있는 경우
		if(bindingResult.hasErrors()) {
			log.info("회원가입 실패");
			
			return "login";
		}else {
			// 없는 경우
			memberService.insertSignUp(signUp); 	// DB에 회원가입 정보 저장
			log.info("회원가입 성공");
			
			return "redirect:login";
		}
	}
	
	// email 사용 가능 여부 검사
	// consumes 속성: 해당 컨트롤러 메서드가 처리할 수 있는 요청의 Content-Type을 지정
	// MediaType.APPLICATION_FORM_URLENCODED_VALUE: Content-Type이 application/x-www-form-urlencoded인 요청만 처리하겠다는 의미
	//												HTML 폼 데이터가 서버로 전송될 때 기본적으로 사용
	@PostMapping(value = "/checkEmail", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody	// 이메일 확인 결과를 JSON형식으로 작성하기 위해 추가
	public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam("email") String email) {
		
		Map<String, Boolean> response = new HashMap<>();
		boolean exists = memberService.checkEmailDuplicate(email);	// 이메일 사용 가능 여부 확인
		
		if(!exists) {
			log.info("이메일 사용 가능");
			response.put("valid", !exists);
		}else {
			log.info("이메일 사용 불가능");
			response.put("valid", !exists);
		}
				
		return ResponseEntity.ok(response);
	}
	
	// uname 사용 가능 여부 검사
	@PostMapping(value = "/checkUname", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody	
	public ResponseEntity<Map<String, Boolean>> checkUname(@RequestParam("uname") String uname) {
		
		Map<String, Boolean> response = new HashMap<>();
		boolean exists = memberService.checkUnameDuplicate(uname);
		
		if(!exists) {
			log.info("닉네임 사용 가능");
			response.put("valid", !exists);
		}else {
			log.info("닉네임 사용 불가능");
			response.put("valid", !exists);
		}
				
		return ResponseEntity.ok(response);
	}
}

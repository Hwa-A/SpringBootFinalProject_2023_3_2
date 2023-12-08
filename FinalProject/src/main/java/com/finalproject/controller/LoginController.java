package com.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.domain.Member;
import com.finalproject.domain.PrincipalDetails;
import com.finalproject.domain.Question;
import com.finalproject.domain.ShowQuestion;
import com.finalproject.service.MemberService;
import com.finalproject.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;
	
	// 루트 주소 입력시 로그인 페이지로 이동
	@GetMapping("/")
	public String firstLoginForm() {		
		return "login";
	}
	
	// 로그인 페이지로 이동
	@RequestMapping("/login")
	public String loginForm() {		
		return "login";
	}
	
	// 회원 탈퇴
	@RequestMapping("/deleteMyAccount")
	public String deleteMyAccount(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		memberService.deleteMyAccount(principalDetails.getEmail());
		
		return "redirect:/login";
	}
}

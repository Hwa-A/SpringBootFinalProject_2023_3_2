package com.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.domain.PrincipalDetails;
import com.finalproject.domain.Question;
import com.finalproject.persistence.QuestionRepository;
import com.finalproject.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired
	private QuestionService questionService;
	
//	@GetMapping("/")
//	public String firstLoginForm() {		
//		return "";
//	}

	@RequestMapping("/login")
	public String loginForm() {		
		return "login";
	}
	
//	@RequestMapping("/home")
//	public String loginSuccessForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
//		log.info("닉네임: "+principalDetails.getUname());
//		log.info("이메일: "+principalDetails.getEmail());
//		
//		model.addAttribute("userEmail", principalDetails.getEmail());
//		model.addAttribute("userName", principalDetails.getUname());
//		
//		return "home";
//	}

	// 로그인 성공한 경우, 메인 페이지로 이동
	@RequestMapping("/home")
	public String loginSuccessForm(Question question, Model model) {
		model.addAttribute("questionList", questionService.getQuestionList(question, "total"));
			
		return "home";
	}
	
}

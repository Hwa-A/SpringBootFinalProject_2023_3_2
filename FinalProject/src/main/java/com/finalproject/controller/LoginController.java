package com.finalproject.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalproject.domain.PrincipalDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@GetMapping("/")
	public String firstLoginForm() {		
		return "login";
	}

	@RequestMapping("/login")
	public String loginForm() {		
		return "login";
	}
	
	@RequestMapping("/home")
	public String loginSuccessForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		log.info("닉네임: "+principalDetails.getUname());
		log.info("이메일: "+principalDetails.getEmail());
		
		model.addAttribute("userEmail", principalDetails.getEmail());
		model.addAttribute("userName", principalDetails.getUname());
		
		return "home";
	}
	
	@RequestMapping("/fail")
	public String loginFailForm() {
		return "fail";
	}
}

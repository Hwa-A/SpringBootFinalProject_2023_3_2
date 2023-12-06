package com.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.domain.Member;
import com.finalproject.domain.PrincipalDetails;
import com.finalproject.domain.Question;
import com.finalproject.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	
	// 질문 목록 보기
	@RequestMapping("/getQuestions")
	public String getQuestionList(@RequestParam(value = "languageCategory", defaultValue = "total") String languageCategory
								, Question question, Model model) {
		model.addAttribute("questionList", questionService.getQuestionList(question, languageCategory));
		
		return "forward:home";
	}
	
	// 질문 작성 페이지로 이동
	@RequestMapping("/register")
	public String registerQuestion() {
		return "/registerQuestion";
	}
	
	// 질문 작성 하기
	@RequestMapping("/insertQuestion")
	public String insertQuestion(@AuthenticationPrincipal PrincipalDetails principalDetails, Question question
								, Model model) {
		String email = principalDetails.getEmail();		// 현재 로그인한 회원의 이메일 가져오기
	
		Member member = new Member();
		member.setEmail(email);		// 이메일 지정

		questionService.insertQuestion(question, member);
				
		return "redirect:home";
	}
	
}

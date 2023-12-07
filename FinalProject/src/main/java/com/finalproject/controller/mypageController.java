package com.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.domain.Member;
import com.finalproject.domain.PrincipalDetails;
import com.finalproject.domain.Question;
import com.finalproject.domain.ShowQuestion;
import com.finalproject.service.QuestionService;

@Controller
public class mypageController {
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/getMyQuestionList")
	public String getMyQuestionList(@AuthenticationPrincipal PrincipalDetails principalDetails,
									@RequestParam(value="page", defaultValue="0") int page, 
									@RequestParam(value="size", defaultValue="2") int size, 
									Model model) {
		// 현재 로그인한 회원 정보
		String currnetEmail = principalDetails.getEmail();	 // 회원 이메일
		
		// 회원 객체 생성
		Member member = new Member();
		member.setEmail(currnetEmail);		// 이메일 지정
		
		// Question의 seq를 기준으로 내림차순(역순) 정렬 조건을 생성
	    Sort sort = Sort.by(Sort.Direction.DESC, "seq");
	    
	    // 페이지 번호, 페이지 크기, 정렬 조건을 가진 PageRequest 객체를 생성
	 	Pageable pageable = PageRequest.of(page, size, sort);
	 	// 페이징 처리 및 정렬을 적용하여 ShowQuestion 리스트를 가져옴
	 	Page<ShowQuestion> paging = questionService.getMyQuestionPage(member, pageable);
	 	   
	 	// 모델에 페이징 처리된 리스트를 저장
	 	model.addAttribute("paging", paging);
	    
		return null;
	}
}

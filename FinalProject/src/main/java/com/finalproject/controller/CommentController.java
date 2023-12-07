package com.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.finalproject.domain.Comment;
import com.finalproject.domain.Member;
import com.finalproject.domain.PrincipalDetails;
import com.finalproject.domain.Question;
import com.finalproject.domain.ShowComment;
import com.finalproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	// 댓글 등록 페이지로 이동
	@RequestMapping("/registerComment")
	public String registerCommentForm(@AuthenticationPrincipal PrincipalDetails principalDetails,
										Question question, Model model) {
		// 현재 로그인한 회원 정보 얻기
		String email = principalDetails.getEmail();		// 회원 이메일
		String uname = principalDetails.getUname();		// 회원 닉네임
		
		Member member = new Member();
		member.setUname(uname);		// 닉네임 지정
		
		model.addAttribute("question", question);
		model.addAttribute("member", member);

		return "registerComment";
	}
			
	// 댓글 등록
	@RequestMapping("/insertComment")
	public String insertComment(@AuthenticationPrincipal PrincipalDetails principalDetails, 
								Question question, Comment comment,
								RedirectAttributes ra) {
		
		String email = principalDetails.getEmail();		// 현재 로그인한 회원 이메일
		
		Member member = new Member();
		member.setEmail(email);		// 이메일 지정

		// 댓글 등록
		commentService.insertComment(comment, question, member);
		// RedirectAttributes 인터페이스: 리다이렉트 시점에 데이터를 임시로 추가 및 관리에 사용
		//							 Flash Attribute를 관리하는 메소드를 제공
		// Flash Attribute: 리다이렉트가 실행되는 동안 데이터를 유지하기 위한 메커니즘
		//					리다이렉트 후에 즉시 제거
		ra.addFlashAttribute("questionSeq", question.getSeq());
		
		return "redirect:/getDetailQuestion";
	}
	
	// 댓글 삭제
	@RequestMapping("/deleteComment")
	public String deleteComment(Comment comment, Question question) {
		
		// Comment 삭제
		commentService.deleteComment(comment);
		
		return "forward:/getDetailQuestion";
	}
}

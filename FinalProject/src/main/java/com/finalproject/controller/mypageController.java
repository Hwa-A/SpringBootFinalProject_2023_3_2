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
import com.finalproject.domain.ShowComment;
import com.finalproject.domain.ShowQuestion;
import com.finalproject.service.CommentService;
import com.finalproject.service.QuestionService;

@Controller
public class mypageController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CommentService commentService;
	
	// 마이페이지 이동 + 현재 로그인한 회원이 작성한 질문 리스트 조회
	@RequestMapping("/myPageForm")
	public String myPageForm(@AuthenticationPrincipal PrincipalDetails principalDetails,
							 @RequestParam(value="page", defaultValue="0") int page, 
							 @RequestParam(value="size", defaultValue="5") int size, 
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
	    
		return "mypage";
	}
	
	// 본인 질문 상세 조회 + 질문에 속하는 모든 댓글 조회
	@RequestMapping("/getDetailMyQuestion")
	public String getDetailMyQuestion(@AuthenticationPrincipal PrincipalDetails principalDetails,
									  @RequestParam(value="page", defaultValue="0") int page, 
									  @RequestParam(value="size", defaultValue="2") int size, 
									  Question question, Model model,
									  @RequestParam(value="questionSeq", defaultValue="0") int questionSeq) {
		/* 질문 상세 조회 */
		// 현재 로그인한 회원 정보
		String currnetEmail = principalDetails.getEmail();	 // 회원 이메일
		String currentUname = principalDetails.getUname();	 // 회원 닉네임
		
		// question 데이터를 제대로 받지 못한 경우
		if(question.getSeq() ==  0) {
			// 댓글 등록 후 사용자의 이름이 바로 보이지 않는 경우 대비(why?fetch = FetchType.LAZY때문)
			question.setSeq(questionSeq);
		}
		
		// 특정 Question 조회
		ShowQuestion findQuestion = questionService.getQuestion(question);
	
		// 질문 작성자와 현재 로그인한 회원이 동일한지 확인
		if(currnetEmail.equals(findQuestion.getWriterEmail())) {
			model.addAttribute("writerCheck", "same");
		}
		model.addAttribute("question", questionService.getQuestion(question));
		model.addAttribute("currentUname", currentUname);
		
		/* 질문에 속하는 모든 댓글 조회 */
		// Comment의 seq를 기준으로 내림차순(역순) 정렬 조건을 생성
	    Sort sort = Sort.by(Sort.Direction.DESC, "ComSeq");
	    
	    // 페이지 번호, 페이지 크기, 정렬 조건을 가진 PageRequest 객체를 생성
	 	Pageable pageable = PageRequest.of(page, size, sort);
	 	// 페이징 처리 및 정렬을 적용하여 ShowComment 리스트를 가져옴
	 	Page<ShowComment> paging = commentService.getCommentPage(question, pageable);
	 	
	 	// 모델에 페이징 처리된 리스트를 저장
	 	model.addAttribute("paging", paging);
	 	
		return "detailMyQuestion";
	}
	
	// 본인 질문 상세 조회: 질문 수정을 위한
	@RequestMapping("/getUpdateMyQuestion")
	public String getUpdateMyQuestion(Question question, Model model) {
		
		// seq를 기준으로 Question 조회
		model.addAttribute("question", questionService.getQuestion(question));
		
		return "updateMyQuestion";
	}
	
	// 본인 질문 수정
	@RequestMapping("/updateMyQuestion")
	public String updateMyQuestion(Question question, Model model) {
		// Question 수정
		questionService.updateQuestion(question);
		
		return "forward:/getDetailMyQuestion";
	}
	
	// 본인 질문 삭제
	@RequestMapping("/deleteMyQuestion")
	public String deleteMyQuestion(Question question) {
			// Question 삭제
		questionService.deleteQuestion(question);
		
		return "forward:/myPageForm";
	}
	
}
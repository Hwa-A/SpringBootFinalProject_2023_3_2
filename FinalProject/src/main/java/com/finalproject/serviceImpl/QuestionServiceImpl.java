package com.finalproject.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.domain.Member;
import com.finalproject.domain.Question;
import com.finalproject.domain.ShowQuestion;
import com.finalproject.persistence.QuestionRepository;
import com.finalproject.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepo;
	
	@Override	// 질문 등록
	public void insertQuestion(Question question, Member member) {
		question.setMember(member);		// 질문 작성자
		question.setCreateDate(new Date());		// 질문 작성일자 
		
		questionRepo.save(question);	
	}

	@Override	// 질문 수정
	public void updateQuestion(Question question) {
		// seq를 기준으로 Question 객체 반환
		Question findQuestion = questionRepo.findById(question.getSeq()).get(); 	
		
		findQuestion.setTitle(question.getTitle()); 		// 질문 제복 변경
		findQuestion.setContent(question.getContent());		// 질문 내용 변경
		findQuestion.setLanguage(question.getLanguage());		// 프로그래밍 언어 변경
		
		questionRepo.save(findQuestion);	
	}

	@Override	// 질문 삭제
	public void deleteQuestion(Question question) {
		questionRepo.deleteById(question.getSeq());
	}

	@Override	// 질문 상세 조회
	public ShowQuestion getQuestion(Question question) {
		Question findQuestion = questionRepo.findById(question.getSeq()).get();
		
		ShowQuestion showQuestion = new ShowQuestion(findQuestion);
		
		return showQuestion;	
	}

	@Override	// 질문 리스트 조회
	public Page<ShowQuestion> getQuestionPage(Question question, Pageable pageable) {	// Pageable: 페이징 처리에 필요한 정보를 담고 있는 인터페이스
		 Page<Question> findQuestionPage;
		
		if(question.getLanguage().equals("total")) {
			// Pageable 인터페이스: 페이징 처리에 필요한 정보를 제공하는 인터페이스
			// pageable에 지정된 페이지 번호와 페이지 크기에 따라 페이징 처리된 모든 결과를 반환
			
			// 모든 질문 가져오기
			findQuestionPage = questionRepo.findAll(pageable); 		
			
		}else {
			// 선택된 프로그래밍 언어에 해당되는 질문 조회
			findQuestionPage = questionRepo.findByLanguage(question.getLanguage(), pageable);
		}
		
		// Question 객체를 ShowQuestion 객체로 변환
		List<ShowQuestion> showQuestionList = new ArrayList<>();
	    for(Question q : findQuestionPage.getContent()) {
	        showQuestionList.add(new ShowQuestion(q));   
	    }

		return new PageImpl<>(showQuestionList, pageable, findQuestionPage.getTotalElements());
	}

	@Override	// 회원이 작성한 질문 가져오기
	public Page<ShowQuestion> getMyQuestionPage(Member member, Pageable pageable) {
		Page<Question> findQuestionPage;
		
		// 회원이 작성한 질문들 조회
		findQuestionPage = questionRepo.findByMember_Email(member.getEmail(), pageable);
		
		// Question 객체를 ShowQuestion 객체로 변환
		List<ShowQuestion> showQuestionList = new ArrayList<>();
		for(Question q : findQuestionPage.getContent()) {
			showQuestionList.add(new ShowQuestion(q));   
		}
		
		return new PageImpl<>(showQuestionList, pageable, findQuestionPage.getTotalElements());
	}
	
}

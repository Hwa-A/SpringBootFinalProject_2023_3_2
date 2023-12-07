package com.finalproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.finalproject.domain.Member;
import com.finalproject.domain.Question;
import com.finalproject.domain.ShowQuestion;

public interface QuestionService {
	public void insertQuestion(Question question, Member member);	// 질문 등록
	public void updateQuestion(Question question);	// 질문 수정
	public void deleteQuestion(Question question);	// 질문 삭제
	public ShowQuestion getQuestion(Question question);	// 질문 상세 조회
	public Page<ShowQuestion> getQuestionPage(Question question, Pageable pageable);	// 질문 리스트 조회
	public Page<ShowQuestion> getMyQuestionPage(Member member, Pageable pageable);		// 로그인한 회원이 작성한 질문 리스트 조회
}
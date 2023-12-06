package com.finalproject.service;

import java.util.List;

import com.finalproject.domain.Member;
import com.finalproject.domain.Question;
import com.finalproject.domain.ShowQuestion;

public interface QuestionService {
	public void insertQuestion(Question question, Member member);	// 질문 등록
	public void updateQuestion(Question question);	// 질문 수정
	public void deleteQuestion(Question question);	// 질문 삭제
	public ShowQuestion getQuestion(Question question);	// 상세 질문 조회
	public List<ShowQuestion> getQuestionList(Question question, String category);	// 질문 리스트 조회

}
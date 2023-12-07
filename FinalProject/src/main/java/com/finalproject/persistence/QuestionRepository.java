package com.finalproject.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	// 해당되는 프로그래밍 언어의 질문들을 가져오기
	Page<Question> findByLanguage(String languageCategory, Pageable pageable);		
	// 회원이 작성한 질문 가져오기
	Page<Question> findByMember_Email(String email, Pageable pageable);
}
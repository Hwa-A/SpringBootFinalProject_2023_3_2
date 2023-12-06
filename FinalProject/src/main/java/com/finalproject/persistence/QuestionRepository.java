package com.finalproject.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.domain.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	List<Question> findByLanguage(String languageCategory);		// 해당되는 프로그래밍 언어의 질문들을 가져오기

}

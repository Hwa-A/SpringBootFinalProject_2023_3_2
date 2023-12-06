package com.finalproject.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		log.error("등록시작");
		question.setMember(member);		// 질문에 작성자 지정
		question.setCreateDate(new Date());
		questionRepo.save(question);	// 저장
		log.error("등록끝");

	}

	@Override	// 질문 수정
	public void updateQuestion(Question question) {
		Question findQuestion = questionRepo.findById(question.getSeq()).get();
		findQuestion.setContent(question.getContent());		// 질문 내용 변경
		findQuestion.setLanguage(question.getLanguage());		// 프로그래밍 언어 변경
		
		questionRepo.save(findQuestion);	// 저장
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
	public List<ShowQuestion> getQuestionList(Question question, String languageCategory) {
		List<Question> findQuestionList;
		
		if(languageCategory.equals("total")) {
			// 모든 질문 찾아오기
			findQuestionList = (List<Question>)questionRepo.findAll();		
			
		}else {
			// 선택된 프로그래밍 언어에 해당되는 질문 조회
			findQuestionList = (List<Question>)questionRepo.findByLanguage(languageCategory);
		}

		List<ShowQuestion> showQuestionList = new ArrayList<>();
		for(Question q : findQuestionList) {
			showQuestionList.add(new ShowQuestion(q));	// Question객체를 ShowQuestion객체 리스트로 변환
		}

		return showQuestionList;
	}

}

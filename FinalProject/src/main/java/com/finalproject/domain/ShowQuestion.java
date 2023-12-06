package com.finalproject.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class ShowQuestion {
	private int seq;
	private String title;		// 질문 제목
	private String content;		// 질문 내용
	private String language;	// 프로그래밍 언어 종류
	private Date createDate;	// 질문 날짜
	private String writer;		// 작성자(Member의 uname)
	
	public ShowQuestion(Question question) {
		this.seq = question.getSeq();
		this.title = question.getTitle();
        this.content = question.getContent();
        this.language = question.getLanguage();
        this.createDate = question.getCreateDate();
        this.writer = question.getMember().getUname();
	}
}

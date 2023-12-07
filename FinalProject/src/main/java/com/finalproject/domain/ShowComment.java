package com.finalproject.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ShowComment {
	private int comSeq;	// 댓글 식별
    private String comContent;  // 댓글 내용
    private Date comCreateDate;	// 댓글 작성 날짜
    private int comQuestionSeq;	// 질문 식별
    private String comWriter;	// 댓글 작성자 
    private String comWriterEmail;	// 댓글 작성자 이메일
	
	public ShowComment(Comment comment) {
		this.comSeq = comment.getComSeq();
        this.comContent = comment.getComContent();
        this.comCreateDate = comment.getComCreateDate();
        this.comQuestionSeq = comment.getQuestion().getSeq();
        this.comWriter = comment.getMember().getUname();
        this.comWriterEmail = comment.getMember().getEmail();
	}
}

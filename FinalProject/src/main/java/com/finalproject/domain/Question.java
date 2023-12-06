package com.finalproject.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "member")
@Entity
public class Question {		// N, 연관관계 주인
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 자동 생성 전략
	private int seq;	// PK
	@NonNull
	private String title;		// 질문 제목
	@NonNull
	private String content;		// 질문 내용
	private String language;		// 프로그래밍 언어 종류
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createDate;	// 질문 날짜
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_email")		// 다대일
	private Member member;		// 질문한 회원
	
}
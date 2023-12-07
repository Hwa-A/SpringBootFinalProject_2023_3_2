package com.finalproject.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = {"member", "commentList"})	// 순환 참조 문제를 위해
@Entity
public class Question {		// N => 연관관계 주인
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
	private Member member;		// 질문 작성 회원
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<Comment> commentList = new ArrayList<Comment>();	// 댓글
	
}
/*
 * FetchType.LAZY(=지연 로딩(Lazy Loading)): 연관된 엔티티를 나중에 필요(사용)할 때 로딩하는 방식
 * 										 연관된 엔티티의 수가 많거나, 필요하지 않은 경우 로딩을 피할 수 있어서 성능 향상에 도움
 * @OneToMany 기본값: FetchType.LAZY
 * @ManyToOne 기본값: FetchType.EAGER
 */
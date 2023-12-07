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
@ToString(exclude = {"member", "question"})
@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comSeq;  // PK

    @NonNull
    private String comContent;  // 댓글 내용

    @Temporal(value=TemporalType.TIMESTAMP)
    private Date comCreateDate;  // 댓글 작성 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_email")  // 댓글 작성 회원
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_seq")  // 속한 질문
    private Question question;
}

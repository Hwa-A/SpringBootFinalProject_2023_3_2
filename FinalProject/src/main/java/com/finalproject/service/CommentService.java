package com.finalproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.finalproject.domain.Comment;
import com.finalproject.domain.Member;
import com.finalproject.domain.Question;
import com.finalproject.domain.ShowComment;

public interface CommentService {
	// 댓글 등록
	public void insertComment(Comment comment, Question question, Member member);	
	// 댓글 삭제
	public void deleteComment(Comment comment);
	// 댓글 리스트 조회
	public Page<ShowComment> getCommentPage(Question question, Pageable pageable);
}

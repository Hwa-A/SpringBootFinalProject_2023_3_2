package com.finalproject.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.domain.Comment;
import com.finalproject.domain.Question;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	// Question의 seq를 기준으로 해당 질문에 속한 모든 Comment 조회
	Page<Comment> findByQuestion_Seq(int seq, Pageable pageable);
}

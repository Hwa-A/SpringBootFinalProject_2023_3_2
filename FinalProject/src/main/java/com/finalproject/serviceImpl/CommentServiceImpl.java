package com.finalproject.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalproject.domain.Comment;
import com.finalproject.domain.Member;
import com.finalproject.domain.Question;
import com.finalproject.domain.ShowComment;
import com.finalproject.persistence.CommentRepository;
import com.finalproject.persistence.QuestionRepository;
import com.finalproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepo;

	@Override	// 댓글 등록
	public void insertComment(Comment comment, Question question, Member member) {
		comment.setQuestion(question);	// 댓글을 단 질문
		comment.setMember(member);		//댓글 작성자
		comment.setComCreateDate(new Date());		// 댓글 작성일자
		
		commentRepo.save(comment);	
	}

	@Override	// 댓글 삭제
	public void deleteComment(Comment comment) {
		commentRepo.deleteById(comment.getComSeq());
	}

	@Override	// 댓글 리스트 조회
	public Page<ShowComment> getCommentPage(Question question, Pageable pageable) {
		Page<Comment> findCommentPage;
		
		// Question의 seq를 기준으로 해당 질문에 속한 모든 Comment 조회
		findCommentPage = commentRepo.findByQuestion_Seq(question.getSeq(), pageable);
		
		// Comment 객체를 ShowComment 객체로 변환
		List<ShowComment> showCommentList = new ArrayList<>();
		for(Comment c : findCommentPage.getContent()) {
			showCommentList.add(new ShowComment(c));
		}
		// PageImpl: Page 인터페이스의 구현체로, 페이징 처리된 데이터와 페이징 정보를 포함하는 객체
		//  ShowComment 리스트와 페이징 정보를 사용해 새로운 Page 객체 생성
		return new PageImpl<>(showCommentList, pageable, findCommentPage.getTotalElements());
		// ShowCommentList: 페이지에 표시할 Comment 리스트
		// pageable: 페이징 요청 정보 포함 객체
		// findCommentPage.getTotalElements(): 전체 Comment 객체의 개수 반환
	}
}

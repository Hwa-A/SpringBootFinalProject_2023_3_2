package com.finalproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.domain.CommentEntity;
import com.finalproject.persistence.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentEntity> getComments() {
        return commentRepository.findAll();
    }

    public CommentEntity getComment(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public CommentEntity saveComment(CommentEntity comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
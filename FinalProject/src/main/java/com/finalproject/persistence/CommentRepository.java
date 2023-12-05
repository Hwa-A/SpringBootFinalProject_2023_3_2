package com.finalproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.domain.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
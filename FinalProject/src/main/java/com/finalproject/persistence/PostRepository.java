package com.finalproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.domain.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
	
	
}
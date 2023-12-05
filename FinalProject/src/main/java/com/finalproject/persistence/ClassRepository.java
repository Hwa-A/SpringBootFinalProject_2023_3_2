package com.finalproject.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.domain.ClassEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
}
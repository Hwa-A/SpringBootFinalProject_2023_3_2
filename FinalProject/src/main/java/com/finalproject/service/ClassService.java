package com.finalproject.service;

import java.io.Console;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.domain.ClassEntity;
import com.finalproject.domain.PostEntity;
import com.finalproject.persistence.ClassRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ClassService {
	
	@Autowired
	private EntityManager entityManager;
    private final ClassRepository classRepository;
    
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<ClassEntity> getClasses() {
    	List<ClassEntity> a = classRepository.findAll();
    	System.out.printf("fdsfadsf",a);
        return classRepository.findAll();
    }
    
    public List<PostEntity> getClassPosts(Long id) {
        ClassEntity classEntity = classRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Could not find ClassEntity with id: " + id));
        return classEntity.getPosts();
    }

    public ClassEntity getClass(Long id) {
        return classRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public ClassEntity saveClass(ClassEntity classEntity) {
    	 ClassEntity savedClass = classRepository.save(classEntity);
    	    entityManager.flush(); // 변경 사항을 명시적으로 플러시
    	    return savedClass;
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }
    
    
}
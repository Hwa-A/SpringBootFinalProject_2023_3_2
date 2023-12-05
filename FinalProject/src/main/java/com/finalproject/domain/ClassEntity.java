package com.finalproject.domain;
	
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
	
	@Entity
	public class ClassEntity {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String name;
	    
	
	    @JsonIgnore
	    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<PostEntity> posts = new ArrayList<>();
	    
	    
	    // Constructor
	    public ClassEntity() {}
	
	    public ClassEntity(String name) {
	        this.name = name;
	    }
	    
	    public void addPost(PostEntity post) {
	        post.setClassEntity(this);
	        posts.add(post);
	    }
	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }
	
	    public void setId(Long id) {
	        this.id = id;
	    }
	
	    public String getName() {
	        return name;
	    }
	
	    public void setName(String name) {
	        this.name = name;
	    }
	    public List<PostEntity> getPosts() {
	        return posts;
	    }

	    public void setPosts(List<PostEntity> posts) {
	        this.posts = posts;
	    }
	}
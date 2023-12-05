package com.finalproject.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
   
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "class_entity_id")
    private ClassEntity classEntity;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private List<CommentEntity> comments = new ArrayList<>();
    

    
 // Constructor
    public PostEntity() {}

    public PostEntity(String content) {
        this.content = content;
    }
    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
        if (classEntity != null) {
            classEntity.getPosts().add(this);
        }
    }
    
    
 
    public Long getId() {
        return id;
    }
	public void setId(Long id) {
        this.id = id;
    }

    public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

package com.finalproject.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.finalproject.domain.PostEntity;
import com.finalproject.persistence.PostRepository;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostEntity> getPosts() {
        return postRepository.findAll();
    }

    public PostEntity getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public PostEntity savePost(PostEntity post) {
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

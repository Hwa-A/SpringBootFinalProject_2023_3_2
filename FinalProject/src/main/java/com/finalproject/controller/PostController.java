package com.finalproject.controller;


import com.finalproject.domain.ClassEntity;
import com.finalproject.domain.PostEntity;
import com.finalproject.service.ClassService;
import com.finalproject.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	private final PostService postService;
    private final ClassService classService; // ClassService 변수 추가

    public PostController(PostService postService, ClassService classService) {
        this.postService = postService;
        this.classService = classService; // ClassService 초기화
    }


    @GetMapping
    public ResponseEntity<List<PostEntity>> getAllPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostEntity> getPost(@PathVariable Long id) {
        PostEntity post = postService.getPost(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity post, @RequestParam("classId") Long classId) {
        try {
            ClassEntity classEntity = classService.getClass(classId);
            post.setClassEntity(classEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        PostEntity createdPost = postService.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}

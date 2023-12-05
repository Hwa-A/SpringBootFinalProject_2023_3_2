package com.finalproject.controller;


import com.finalproject.domain.CommentEntity;
import com.finalproject.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentEntity>> getAllComments() {
        return new ResponseEntity<>(commentService.getComments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentEntity> getComment(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.getComment(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentEntity> createComment(@RequestBody CommentEntity comment) {
        return new ResponseEntity<>(commentService.saveComment(comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
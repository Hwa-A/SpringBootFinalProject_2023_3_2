package com.finalproject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.domain.ClassEntity;
import com.finalproject.domain.PostEntity;
import com.finalproject.service.ClassService;

@RestController
@RequestMapping("/api/classes")
public class ClassController {
private final ClassService classService;

public ClassController(ClassService classService) {
    this.classService = classService;
}

@GetMapping
public ResponseEntity<List<ClassEntity>> getAllClasses() {
    return new ResponseEntity<>(classService.getClasses(), HttpStatus.OK);
}


@GetMapping("/{id}")
public ResponseEntity<ClassEntity> getClass(@PathVariable Long id) {
    return new ResponseEntity<>(classService.getClass(id), HttpStatus.OK);
}

@GetMapping("/{id}/posts")
public ResponseEntity<List<PostEntity>> getClassPosts(@PathVariable Long id) {
    return new ResponseEntity<>(classService.getClassPosts(id), HttpStatus.OK);
}

@PostMapping
public ResponseEntity<ClassEntity> createClass(@RequestBody ClassEntity classEntity) {
    return new ResponseEntity<>(classService.saveClass(classEntity), HttpStatus.CREATED);
}

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteClass(@PathVariable Long id) {
    classService.deleteClass(id);
    return new ResponseEntity<>(HttpStatus.OK);
}
}
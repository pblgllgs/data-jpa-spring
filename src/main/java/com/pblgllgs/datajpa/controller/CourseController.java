package com.pblgllgs.datajpa.controller;

import com.pblgllgs.datajpa.entity.Course;
import com.pblgllgs.datajpa.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/v1")
    public ResponseEntity<List<Course>> findAll(){
        return new ResponseEntity<>(courseRepository.findAll(), HttpStatus.OK);
    }
}

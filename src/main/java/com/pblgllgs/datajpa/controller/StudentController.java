package com.pblgllgs.datajpa.controller;

import com.pblgllgs.datajpa.entity.Student;
import com.pblgllgs.datajpa.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/v1/{id}")
    public Student findById(@PathVariable("id") Long id){
        return studentRepository.findById(id).orElseThrow( () -> new RuntimeException("no encontrado"));
    }
}

package com.pblgllgs.datajpa.controller;

import com.pblgllgs.datajpa.entity.StudentIdCad;
import com.pblgllgs.datajpa.repository.StudentIdCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/studentIdCard")
public class StudentIdCardController {
    private final StudentIdCardRepository studentIdCardRepository;

    public StudentIdCardController(StudentIdCardRepository studentIdCardRepository) {
        this.studentIdCardRepository = studentIdCardRepository;
    }


    @GetMapping("/v1/{id}")
    public ResponseEntity<StudentIdCad> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                studentIdCardRepository
                        .findById(id)
                        .orElseThrow( () -> new RuntimeException("no encontrado")), HttpStatus.OK);
    }
}

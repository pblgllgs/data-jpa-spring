package com.pblgllgs.datajpa.controller;

import com.pblgllgs.datajpa.entity.Book;
import com.pblgllgs.datajpa.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                bookRepository
                        .findById(id)
                        .orElseThrow( () -> new RuntimeException("no encontrado")), HttpStatus.OK);
    }
}

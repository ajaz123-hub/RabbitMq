package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Book;
import com.test.repo.BookRepository;
import com.test.service.BookConsumer;
import com.test.service.BookProducer;

@RestController
@RequestMapping("/books")
public class BookController {
//    @Autowired
//    private BookRepository bookRepository;

    @Autowired
    private BookProducer bookProducer;
    
    @Autowired
    private BookConsumer bookConsumer;

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        bookProducer.sendCreateBookMessage(book);
        return ResponseEntity.ok("Book creation request sent.");
    }
    
}

   


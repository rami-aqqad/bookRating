package com.aiinarabic.bookinfoservice.controllers;

import com.aiinarabic.bookinfoservice.models.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @RequestMapping("/{bookId}")
    public Book getBookInfo(@PathVariable("bookId") String bookId){
        return new Book(bookId, "Test name");
    }
}

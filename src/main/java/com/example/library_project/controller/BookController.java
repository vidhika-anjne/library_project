package com.example.library_project.controller;

import com.example.library_project.dto.BookResponseDTO;
import com.example.library_project.entity.Book;
import com.example.library_project.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponseDTO> getAllBooks(){
        return bookService.getAllBooksDTO();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
}

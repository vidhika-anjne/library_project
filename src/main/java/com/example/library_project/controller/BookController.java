package com.example.library_project.controller;

import com.example.library_project.dto.BookResponseDTO;
import com.example.library_project.dto.CreateBookRequest;
import com.example.library_project.entity.Book;
import com.example.library_project.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<BookResponseDTO> getAllBooks(){
        return bookService.getAllBooksDTO();
    }

//    @PostMapping
//    public Book addBook(@RequestBody Book book){
//        return bookService.addBook(book);
//    }
    @PostMapping
    public BookResponseDTO addBook(@Valid @RequestBody CreateBookRequest request) {
        return bookService.addBook(request);
    }

    @GetMapping
    public Page<BookResponseDTO> getBooks(
            @RequestParam(required = false) String q,
            Pageable pageable
    ) {
        return bookService.searchBooks(q, pageable);
    }
}

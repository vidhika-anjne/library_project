package com.example.library_project.service;

import com.example.library_project.dto.BookResponseDTO;
import com.example.library_project.entity.Author;
import com.example.library_project.entity.Book;
import com.example.library_project.repository.AuthorRepository;
import com.example.library_project.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book addBook(Book book) {
        Author incomingAuthor = book.getAuthor();

        Author author = authorRepository
                .findByName(incomingAuthor.getName())
                .orElseGet(() -> authorRepository.save(incomingAuthor));

        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<BookResponseDTO> getAllBooksDTO() {
        return bookRepository.findAll()
                .stream()
                .map(book -> new BookResponseDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getIsbn(),
                        book.getAvailableCopies(),
                        book.getAuthor() != null ? book.getAuthor().getName() : null

                ))
                .toList();
    }
}
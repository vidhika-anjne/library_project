package com.example.library_project.service;

import com.example.library_project.dto.BookResponseDTO;
import com.example.library_project.dto.CreateBookRequest;
import com.example.library_project.entity.Author;
import com.example.library_project.entity.Book;
import com.example.library_project.exception.ConflictException;
import com.example.library_project.repository.AuthorRepository;
import com.example.library_project.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    public Book addBook(CreateBookRequest request) {
//        Author incomingAuthor = book.getAuthor();
//
//        Author author = authorRepository
//                .findByName(incomingAuthor.getName())
//                .orElseGet(() -> authorRepository.save(incomingAuthor));
//
//        book.setAuthor(author);
//        return bookRepository.save(book);
//    }
    public BookResponseDTO addBook(CreateBookRequest request) {

        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new ConflictException("Book with this ISBN already exists");
        }

        Author author = authorRepository
                .findByName(request.getAuthorName())
                .orElseGet(() -> authorRepository.save(
                        new Author(request.getAuthorName())
                ));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies());
        book.setAuthor(author);

        Book saved = bookRepository.save(book);

        return new BookResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getIsbn(),
                saved.getAvailableCopies(),
                author.getName()
        );
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

    public Page<BookResponseDTO> searchBooks(
            String query,
            Pageable pageable
    ) {

        Page<Book> page;

        if (query == null || query.isBlank()) {
            page = bookRepository.findAll(pageable);
        } else {
            page = bookRepository.findByTitleContainingIgnoreCase(query, pageable);
        }

        return page.map(book -> new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getAvailableCopies(),
                book.getAuthor() != null ? book.getAuthor().getName() : null
        ));
    }
}
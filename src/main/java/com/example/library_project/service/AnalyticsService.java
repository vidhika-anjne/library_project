package com.example.library_project.service;

import com.example.library_project.dto.AuthorBookCountDTO;
import com.example.library_project.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final AuthorRepository authorRepository;

    public AnalyticsService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorBookCountDTO> getBooksPerAuthor() {
        return authorRepository.findAuthorBookCounts();
    }
}

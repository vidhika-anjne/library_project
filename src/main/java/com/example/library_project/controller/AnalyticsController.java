package com.example.library_project.controller;

import com.example.library_project.dto.AuthorBookCountDTO;
import com.example.library_project.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/books-per-author")
    public List<AuthorBookCountDTO> getBooksPerAuthor() {
        return analyticsService.getBooksPerAuthor();
    }
}


package com.example.library_project.dto;

public class BookResponseDTO {

    private Long id;
    private String title;
    private String isbn;
    private int availableCopies;
    private String authorName;

    public BookResponseDTO(Long id, String title, String isbn,
                           int availableCopies, String authorName) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.availableCopies = availableCopies;
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public String getAuthorName() {
        return authorName;
    }
}

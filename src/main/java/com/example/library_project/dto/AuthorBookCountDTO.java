package com.example.library_project.dto;

public class AuthorBookCountDTO {

    private String authorName;
    private long totalBooks;

    public AuthorBookCountDTO(String authorName, long totalBooks) {
        this.authorName = authorName;
        this.totalBooks = totalBooks;
    }

    public String getAuthorName() {
        return authorName;
    }

    public long getTotalBooks() {
        return totalBooks;
    }
}

package com.example.library_project.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateBookRequest {

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "ISBN must not be blank")
    private String isbn;

    @NotNull(message = "Total copies is required")
    @Min(value = 1, message = "Total copies must be at least 1")
    private Integer totalCopies;

    @NotBlank(message = "Author name must not be blank")
    private String authorName;

    // getters only (immutable input preferred)
    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public String getAuthorName() {
        return authorName;
    }
}

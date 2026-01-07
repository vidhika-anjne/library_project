package com.example.library_project.dto;

import com.example.library_project.entity.IssueStatus;
import java.time.LocalDate;

public class IssueHistoryResponseDTO {

    private Long issueId;
    private String bookTitle;
    private String isbn;
    private IssueStatus status;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssueHistoryResponseDTO(
            Long issueId,
            String bookTitle,
            String isbn,
            IssueStatus status,
            LocalDate issueDate,
            LocalDate returnDate
    ) {
        this.issueId = issueId;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.status = status;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public Long getIssueId() {
        return issueId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}

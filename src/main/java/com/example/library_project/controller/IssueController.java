package com.example.library_project.controller;

import com.example.library_project.entity.IssueRecord;
import com.example.library_project.service.IssueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<IssueRecord> getAllIssues() {
        return issueService.getAllIssues();
    }

//    @PostMapping("/{bookId}")
//    public IssueRecord issueBook(@PathVariable Long bookId) {
//        return issueService.issueBook(bookId);
//    }

    @PutMapping("/{issueId}/member/{memberId}/return")
    public IssueRecord returnBook(@PathVariable Long issueId, @PathVariable Long memberId) {
        return issueService.returnBook(issueId, memberId);
    }

    @PostMapping("/{bookId}/member/{memberId}")
    public IssueRecord issueBook(
            @PathVariable Long bookId,
            @PathVariable Long memberId
    ) {
        return issueService.issueBook(bookId, memberId);
    }

}

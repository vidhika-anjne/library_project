package com.example.library_project.service;

import com.example.library_project.entity.*;
import com.example.library_project.exception.BadRequestException;
import com.example.library_project.exception.ConflictException;
import com.example.library_project.exception.ResourceNotFoundException;
import com.example.library_project.repository.BookRepository;
import com.example.library_project.repository.IssueRecordRepository;
import com.example.library_project.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {

    private final BookRepository bookRepository;
    private final IssueRecordRepository issueRecordRepository;
    private final MemberRepository memberRepository;

    public IssueService(BookRepository bookRepository,
                        IssueRecordRepository issueRecordRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.issueRecordRepository = issueRecordRepository;
        this.memberRepository = memberRepository;
    }

    public List<IssueRecord> getAllIssues() {
        return issueRecordRepository.findAll();
    }

    @Transactional
    public IssueRecord issueBook(Long bookId, Long memberId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        if (!member.isActive()) {
            throw new BadRequestException("Member is not active");
        }

        if (book.getAvailableCopies() <= 0) {
            throw new ConflictException("No copies available");
        }

        long issuedCount = issueRecordRepository
                .countByMemberAndStatus(member, IssueStatus.ISSUED);

        if (issuedCount >= 3) {
            throw new ConflictException("Member has reached issue limit");
        }

        boolean alreadyIssued = issueRecordRepository
                .existsByBookAndMemberAndStatus(book, member, IssueStatus.ISSUED);

        if (alreadyIssued) {
            throw new ConflictException("This book is already issued to the member");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        IssueRecord record = new IssueRecord();
        record.setBook(book);
        record.setMember(member);
        record.setIssueDate(LocalDate.now());
        record.setStatus(IssueStatus.ISSUED);

        bookRepository.save(book);
        return issueRecordRepository.save(record);
    }

    @Transactional
    public IssueRecord returnBook(Long issueRecordId) {

        IssueRecord record = issueRecordRepository.findById(issueRecordId)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        if (record.getStatus() == IssueStatus.RETURNED) {
            throw new BadRequestException("Book already returned");
        }

        Book book = record.getBook();

        book.setAvailableCopies(book.getAvailableCopies() + 1);

        record.setStatus(IssueStatus.RETURNED);
        record.setReturnDate(LocalDate.now());

        bookRepository.save(book);
        return issueRecordRepository.save(record);
    }

}

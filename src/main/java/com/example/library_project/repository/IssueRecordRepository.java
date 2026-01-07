package com.example.library_project.repository;


import com.example.library_project.entity.Book;
import com.example.library_project.entity.IssueRecord;
import com.example.library_project.entity.IssueStatus;
import com.example.library_project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {
    long countByMemberAndStatus(Member member, IssueStatus status);

    boolean existsByBookAndMemberAndStatus(
            Book book,
            Member member,
            IssueStatus status
    );
    List<IssueRecord> findByMember(Member member);
}


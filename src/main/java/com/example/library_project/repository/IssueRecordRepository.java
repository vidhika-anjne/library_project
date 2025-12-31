package com.example.library_project.repository;


import com.example.library_project.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {

}


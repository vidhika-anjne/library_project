package com.example.library_project.service;

import com.example.library_project.dto.BookResponseDTO;
import com.example.library_project.dto.CreateMemberRequest;
import com.example.library_project.dto.IssueHistoryResponseDTO;
import com.example.library_project.dto.MemberResponseDTO;
import com.example.library_project.entity.Member;
import com.example.library_project.exception.ConflictException;
import com.example.library_project.exception.ResourceNotFoundException;
import com.example.library_project.repository.IssueRecordRepository;
import com.example.library_project.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final IssueRecordRepository issueRecordRepository;

    public MemberService(MemberRepository memberRepository, IssueRecordRepository issueRecordRepository) {
        this.memberRepository = memberRepository;
        this.issueRecordRepository = issueRecordRepository;
    }

    public MemberResponseDTO createMember(CreateMemberRequest request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Member with this email already exists");
        }

        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());

        Member saved = memberRepository.save(member);

        return new MemberResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.isActive()
        );
    }

    public List<MemberResponseDTO> getAllMembersDTO() {
        return memberRepository.findAll()
                .stream()
                .map(member -> new MemberResponseDTO(
                        member.getId(),
                        member.getName(),
                        member.getEmail(),
                        member.isActive()
                ))
                .toList();
    }

    public List<IssueHistoryResponseDTO> getMemberIssueHistory(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        return issueRecordRepository.findByMember(member)
                .stream()
                .map(record -> new IssueHistoryResponseDTO(
                        record.getId(),
                        record.getBook().getTitle(),
                        record.getBook().getIsbn(),
                        record.getStatus(),
                        record.getIssueDate(),
                        record.getReturnDate()
                ))
                .toList();
    }

}

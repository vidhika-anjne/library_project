package com.example.library_project.controller;

import com.example.library_project.dto.CreateMemberRequest;
import com.example.library_project.dto.IssueHistoryResponseDTO;
import com.example.library_project.dto.MemberResponseDTO;
import com.example.library_project.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public MemberResponseDTO createMember(
            @Valid @RequestBody CreateMemberRequest request) {
        return memberService.createMember(request);
    }

    @GetMapping
    public List<MemberResponseDTO> getMembers(){
        return memberService.getAllMembersDTO();
    }

    @GetMapping("/{memberId}/issues")
    public List<IssueHistoryResponseDTO> getMemberIssueHistory(
            @PathVariable Long memberId
    ) {
        return memberService.getMemberIssueHistory(memberId);
    }

}

package org.sopt.cloneCoding.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.sopt.cloneCoding.domain.Member;
import org.sopt.cloneCoding.service.MemberService;
import org.sopt.cloneCoding.service.dto.MemberCreateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity createMember(
            @RequestBody MemberCreateDto memberCreateDto
    ){
        return ResponseEntity.created(URI.create(memberService.createMember(memberCreateDto))).build();
    }
}

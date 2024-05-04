package opt.sopt.practice.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import opt.sopt.practice.service.MemberService;
import opt.sopt.practice.service.dto.MemberCreateDto;
import opt.sopt.practice.service.dto.MemberFindAllDto;
import opt.sopt.practice.service.dto.MemberFindDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity createMember(
            @RequestBody MemberCreateDto memberCreateDto
    ) {
        return ResponseEntity.created(URI.create(memberService.createMember(memberCreateDto))).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberFindAllDto>> getMemberAll(

    ) {
        return ResponseEntity.ok(memberService.findMemberAll());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberFindDto> getMemberById(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.noContent().build();
    }
}

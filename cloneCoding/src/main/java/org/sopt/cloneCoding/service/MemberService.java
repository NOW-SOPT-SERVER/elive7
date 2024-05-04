package org.sopt.cloneCoding.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.cloneCoding.domain.Member;
import org.sopt.cloneCoding.repository.MemberRepository;
import org.sopt.cloneCoding.service.dto.MemberCreateDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public String createMember(MemberCreateDto memberCreateDto){
        Member member = Member.builder()
                .userId(memberCreateDto.userId())
                .name(memberCreateDto.name())
                .age(memberCreateDto.age())
                .build();
        memberRepository.save(member);
        return member.getId().toString();
    }

    public Member findMemberById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                ()->new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        );
    }
}

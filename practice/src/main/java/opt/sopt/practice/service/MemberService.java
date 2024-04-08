package opt.sopt.practice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import opt.sopt.practice.domain.Member;
import opt.sopt.practice.repository.MemberRepository;
import opt.sopt.practice.service.dto.MemberCreateDto;
import opt.sopt.practice.service.dto.MemberFindDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public  String createMember( MemberCreateDto memberCreateDto){
        Member member = Member.create(memberCreateDto.name(),memberCreateDto.part(), memberCreateDto.age());
        memberRepository.save(member);
        return  member.getId().toString();
    }

    public MemberFindDto findMemberById(
            Long memberId
    ) {
        return MemberFindDto.of(memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        ));
    }
    @Transactional
    public void deleteMemberById(Long memerId){
        Member member = memberRepository.findById(memerId)
                .orElseThrow(()->new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다."));
        memberRepository.delete(member);
    }
}

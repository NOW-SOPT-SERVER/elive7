package opt.sopt.practice.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import opt.sopt.practice.auth.UserAuthentication;
import opt.sopt.practice.auth.rds.domain.Token;
import opt.sopt.practice.auth.rds.repository.TokenRepository;
import opt.sopt.practice.common.dto.ErrorMessage;
import opt.sopt.practice.common.jwt.JwtTokenProvider;
import opt.sopt.practice.domain.Member;
import opt.sopt.practice.exception.NotFoundException;
import opt.sopt.practice.repository.MemberRepository;
import opt.sopt.practice.service.dto.MemberCreateDto;
import opt.sopt.practice.service.dto.MemberFindAllDto;
import opt.sopt.practice.service.dto.MemberFindDto;
import opt.sopt.practice.service.dto.UserJoinResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserJoinResponse createMember(
            MemberCreateDto memberCreate
    ) {
        Member member = memberRepository.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        Long memberId = member.getId();
        //Access Token 생성
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        //Refresh Token 생성
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        //Refresh Token 저장
        tokenRepository.save(Token.of(memberId, refreshToken));

        return UserJoinResponse.of(accessToken, refreshToken, memberId.toString());
    }

    public List<MemberFindAllDto> findMemberAll() {
        return MemberFindAllDto.findAll(memberRepository.findAll());
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        );
    }

    public MemberFindDto findMemberById(
            Long memberId
    ) {
        return MemberFindDto.of(memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        ));
    }

    @Transactional
    public void deleteMemberById(Long memerId) {
        Member member = memberRepository.findById(memerId)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다."));
        memberRepository.delete(member);
    }
}

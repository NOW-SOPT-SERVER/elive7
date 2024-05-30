package opt.sopt.practice.auth.rds.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import opt.sopt.practice.auth.UserAuthentication;
import opt.sopt.practice.auth.rds.domain.Token;
import opt.sopt.practice.auth.rds.repository.TokenRepository;
import opt.sopt.practice.auth.rds.service.dto.AccessTokenResponse;
import opt.sopt.practice.common.dto.ErrorMessage;
import opt.sopt.practice.common.jwt.JwtTokenProvider;
import opt.sopt.practice.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public Token findByRefreshToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken).orElseThrow(
                () -> new NotFoundException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND)
        );
    }

    public AccessTokenResponse refreshAccessToken(String refreshToken) {
        Token token = findByRefreshToken(refreshToken);
        Long memberId = token.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(UserAuthentication.createUserAuthentication(memberId));
        return AccessTokenResponse.of(accessToken);
    }
}

package opt.sopt.practice.auth.rds.controller;

import lombok.RequiredArgsConstructor;
import opt.sopt.practice.auth.rds.domain.Token;
import opt.sopt.practice.auth.rds.service.TokenService;
import opt.sopt.practice.auth.rds.service.dto.AccessTokenResponse;
import opt.sopt.practice.auth.rds.service.dto.RefreshTokenRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/token/refresh")
    public ResponseEntity<AccessTokenResponse> refreshAccessToken(
            @RequestHeader String refreshToken) {
        return ResponseEntity.ok(tokenService.refreshAccessToken(refreshToken));
    }
}

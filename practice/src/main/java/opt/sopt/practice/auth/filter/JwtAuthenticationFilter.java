package opt.sopt.practice.auth.filter;

import static opt.sopt.practice.common.jwt.JwtValidationType.EXPIRED_JWT_TOKEN;
import static opt.sopt.practice.common.jwt.JwtValidationType.VALID_JWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import opt.sopt.practice.auth.UserAuthentication;
import opt.sopt.practice.common.dto.ErrorMessage;
import opt.sopt.practice.common.dto.ErrorResponse;
import opt.sopt.practice.common.jwt.JwtTokenProvider;
import opt.sopt.practice.exception.AccessTokenExpiredException;
import opt.sopt.practice.exception.UnauthorizedException;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = getJwtFromRequest(request);
            if (jwtTokenProvider.validateToken(token) == VALID_JWT) {
                Long memberId = jwtTokenProvider.getUserFromJwt(token);
                UserAuthentication authentication = UserAuthentication.createUserAuthentication(memberId);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (jwtTokenProvider.validateToken(token) == EXPIRED_JWT_TOKEN) {
                SecurityContextHolder.clearContext();
                //throw new AccessTokenExpiredException(ErrorMessage.JWT_ACCESS_TOKEN_EXPIRED_EXCEPTION);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(
                        ErrorResponse.of(ErrorMessage.JWT_ACCESS_TOKEN_EXPIRED_EXCEPTION.getStatus(),
                                ErrorMessage.JWT_ACCESS_TOKEN_EXPIRED_EXCEPTION.getMessage())));
                return;

            }
        } catch (Exception exception) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}

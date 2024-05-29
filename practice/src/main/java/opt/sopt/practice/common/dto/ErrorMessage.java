package opt.sopt.practice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사용자가 존재하지 않습니다."),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 블로그가 존재하지 않습니다."),
    BLOG_NOT_OWNED_BY_USER(HttpStatus.FORBIDDEN.value(), "이 블로그에 대한 권한이 없습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 게시글이 존재하지 않습니다."),

    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다."),
    ;
    private final int status;
    private final String message;
}

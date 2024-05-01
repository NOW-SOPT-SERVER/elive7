package opt.sopt.practice.service.dto;

import jakarta.validation.constraints.Size;

public record PostCreateRequest(
        @Size(min = 1, max = 20, message = "게시글 제목이 최대 글자 수(10자)를 초과했습니다.")
        String name,
        @Size(min = 1, max = 200, message = "게시글 본문이 최대 글자 수(200자)를 초과했습니다.")
        String content) {
}

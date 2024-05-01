package opt.sopt.practice.service.dto;

import jakarta.validation.constraints.Size;

public record PostCreateRequest(
        @Size(min = 1, max = 20, message = "게시글 제목은 최소 1자 최대 20자여야 합니다.")
        String name,
        @Size(min = 1, max = 200, message = "게시글 내용은 최소 1자 최대 200자여야 합니다.")
        String content) {
}

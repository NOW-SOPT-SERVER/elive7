package opt.sopt.practice.service.dto;

import opt.sopt.practice.domain.Post;

public record PostFindResponse(String name, String content) {
    public static PostFindResponse of(Post post) {
        return new PostFindResponse(post.getName(), post.getContent());
    }
}

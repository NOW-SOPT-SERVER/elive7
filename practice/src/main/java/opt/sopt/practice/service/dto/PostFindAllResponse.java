package opt.sopt.practice.service.dto;

import java.util.List;
import java.util.stream.Collectors;
import opt.sopt.practice.domain.Post;

public record PostFindAllResponse(String name, String content) {
    public static List<PostFindAllResponse> findAll(List<Post> posts) {
        List<PostFindAllResponse> list = posts.stream().map(PostFindAllResponse::of).collect(Collectors.toList());
        return list;
    }

    public static PostFindAllResponse of(Post post) {
        return new PostFindAllResponse(post.getName(), post.getContent());
    }
}

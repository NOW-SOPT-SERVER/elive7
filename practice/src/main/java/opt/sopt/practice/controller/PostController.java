package opt.sopt.practice.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import opt.sopt.practice.common.dto.SuccessMessage;
import opt.sopt.practice.common.dto.SuccessStatusResponse;
import opt.sopt.practice.service.PostService;
import opt.sopt.practice.service.dto.PostCreateRequest;
import opt.sopt.practice.service.dto.PostFindAllResponse;
import opt.sopt.practice.service.dto.PostFindResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<SuccessStatusResponse> createPost(@RequestHeader Long memberId,
                                                            @RequestHeader Long blogId,
                                                            @Valid @RequestBody PostCreateRequest postCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", postService.create(memberId, blogId, postCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.POST_CREATE_SUCCESS));
    }

    //게시글 id로 게시글 하나 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<SuccessStatusResponse<PostFindResponse>> getPostById(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessStatusResponse.of(SuccessMessage.POST_GET_SUCCESS, postService.findPostById(postId)));
    }


    //blogId가 가리키는 블로그에 있는 모든 게시글 조회
    @GetMapping("post/all/{blogId}")
    public ResponseEntity<SuccessStatusResponse<List<PostFindAllResponse>>> getPostByBlogId(@PathVariable Long blogId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessStatusResponse.of(SuccessMessage.POST_GET_SUCCESS, postService.findPostByBlogId(blogId)));
    }
}

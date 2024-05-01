package opt.sopt.practice.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import opt.sopt.practice.common.dto.ErrorMessage;
import opt.sopt.practice.domain.Blog;
import opt.sopt.practice.domain.Member;
import opt.sopt.practice.domain.Post;
import opt.sopt.practice.exception.NotFoundException;
import opt.sopt.practice.exception.UnauthorizedActionException;
import opt.sopt.practice.repository.PostRepository;
import opt.sopt.practice.service.dto.MemberFindAllDto;
import opt.sopt.practice.service.dto.PostCreateRequest;
import opt.sopt.practice.service.dto.PostFindAllResponse;
import opt.sopt.practice.service.dto.PostFindResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BlogService blogService;
    private final MemberService memberService;

    @Transactional
    public String create(Long memberId, Long blogId, PostCreateRequest postCreateRequest) {
        Member member = memberService.findById(memberId);
        Blog blog = blogService.findBlogById(blogId);

        //사용자가 해당 블로그를 소유하고 있는지 확인
        if (member != blog.getMember()) {
            throw new UnauthorizedActionException(ErrorMessage.BLOG_NOT_OWNED_BY_USER);
        }

        Post post = postRepository.save(Post.create(blog, postCreateRequest));
        return post.getId().toString();
    }

    public PostFindResponse findPostById(Long postId) {
        return PostFindResponse.of(postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.POST_NOT_FOUND)
        ));
    }

    public List<PostFindAllResponse> findPostByBlogId(Long blogId) {
        blogService.findBlogById(blogId); //blogId에 해당하는 블로그가 없을 경우 오류 출력을 위함
        return PostFindAllResponse.findAll(postRepository.findAllByBlog_Id(blogId));
    }
}

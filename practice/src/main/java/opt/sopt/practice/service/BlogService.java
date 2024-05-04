package opt.sopt.practice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import opt.sopt.practice.common.dto.ErrorMessage;
import opt.sopt.practice.domain.Blog;
import opt.sopt.practice.domain.Member;
import opt.sopt.practice.exception.NotFoundException;
import opt.sopt.practice.repository.BlogRepository;
import opt.sopt.practice.service.dto.BlogCreateRequest;
import opt.sopt.practice.service.dto.BlogTitleUpdateRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final MemberService memberService;

    @Transactional
    public String create(Long memberId, BlogCreateRequest blogCreateRequest) {
        Member member = memberService.findById(memberId);
        Blog blog = blogRepository.save(Blog.create(member, blogCreateRequest));
        return blog.getId().toString();
    }

    public Blog findBlogById(Long blogId) {
        return blogRepository.findById(blogId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND)
        );
    }

    @Transactional
    public void updateTitle(Long blogId, BlogTitleUpdateRequest blogTitleUpdateRequest) {
        Blog blog = findBlogById(blogId);
        blog.updateTitle(blogTitleUpdateRequest.title());
    }
}

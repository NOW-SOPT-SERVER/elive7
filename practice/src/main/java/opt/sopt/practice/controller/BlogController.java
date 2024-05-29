package opt.sopt.practice.controller;

import jakarta.validation.Valid;
import java.net.URI;
import opt.sopt.practice.auth.PrincipalHandler;
import opt.sopt.practice.common.dto.SuccessMessage;
import opt.sopt.practice.common.dto.SuccessStatusResponse;
import lombok.RequiredArgsConstructor;
import opt.sopt.practice.service.BlogService;
import opt.sopt.practice.service.dto.BlogCreateRequest;
import opt.sopt.practice.service.dto.BlogTitleUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    private final PrincipalHandler principalHandler;

    @PostMapping("/blog")
    public ResponseEntity createBlog(
            BlogCreateRequest blogCreateRequest
    ) {
        return ResponseEntity.created(URI.create(blogService.create(
                principalHandler.getUserIdFromPrincipal(), blogCreateRequest))).build();
    }

    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(@PathVariable Long blogId,
                                          @Valid @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }
}

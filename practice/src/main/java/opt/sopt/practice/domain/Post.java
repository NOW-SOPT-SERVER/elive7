package opt.sopt.practice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import opt.sopt.practice.service.dto.PostCreateRequest;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTImeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    private Post(Blog blog, String name, String content) {
        this.name = name;
        this.content = content;
        this.blog = blog;
    }

    public static Post create(Blog blog, PostCreateRequest postCreateRequest) {
        return new Post(blog, postCreateRequest.name(), postCreateRequest.content());
    }
}

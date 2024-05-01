package opt.sopt.practice.repository;

import java.util.List;
import opt.sopt.practice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByBlog_Id(Long blogId); // Blog 엔티티의 id로 Post 목록 검색
}

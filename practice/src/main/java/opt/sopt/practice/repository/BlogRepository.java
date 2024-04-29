package opt.sopt.practice.repository;

import opt.sopt.practice.domain.Blog;
import opt.sopt.practice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}

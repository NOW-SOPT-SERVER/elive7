package org.sopt.cloneCoding.repository;

import java.util.Optional;
import org.sopt.cloneCoding.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByMember_IdAndProduct_Id(Long memberId, Long productId);
}

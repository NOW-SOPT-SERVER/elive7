package org.sopt.cloneCoding.repository;

import org.sopt.cloneCoding.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}


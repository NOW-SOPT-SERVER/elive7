package org.sopt.cloneCoding.repository;

import org.sopt.cloneCoding.domain.SellingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellingProcuctRepository extends JpaRepository<SellingProduct, Long> {
}

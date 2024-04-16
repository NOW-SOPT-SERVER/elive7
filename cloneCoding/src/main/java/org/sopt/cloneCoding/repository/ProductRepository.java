package org.sopt.cloneCoding.repository;

import org.sopt.cloneCoding.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

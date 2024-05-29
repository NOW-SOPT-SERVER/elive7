package org.sopt.cloneCoding.repository;

import java.util.List;
import org.sopt.cloneCoding.domain.Product;
import org.sopt.cloneCoding.domain.TransactionPlace;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Slice<Product> findAllByTransactionPlace(TransactionPlace transactionPlace, Pageable pageable);
}

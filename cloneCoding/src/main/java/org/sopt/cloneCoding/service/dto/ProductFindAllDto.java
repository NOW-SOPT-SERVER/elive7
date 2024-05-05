package org.sopt.cloneCoding.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.sopt.cloneCoding.domain.Member;
import org.sopt.cloneCoding.domain.Product;
import org.sopt.cloneCoding.domain.SellingProduct;
import org.sopt.cloneCoding.domain.SharingProduct;
import org.sopt.cloneCoding.domain.TransactionPlace;
import org.springframework.data.domain.Slice;
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public record ProductFindAllDto(String type, String title, Member seller, Optional<Double> price, String description, TransactionPlace transactionPlace, Optional<Boolean> negotiable, Optional<Boolean> sharingEvent, LocalDateTime createdAt){
    public static Slice<ProductFindAllDto> findAll(Slice<Product> products){
        Slice<ProductFindAllDto> slice = products.map(ProductFindAllDto::of);
        return slice;
    }
    public static ProductFindAllDto of(Product product){
        if (product instanceof SellingProduct){
            SellingProduct sp = (SellingProduct) product;
            return new ProductFindAllDto(
                    "Selling",
                    sp.getTitle(),
                    sp.getSeller(),
                    Optional.of(sp.getPrice()),
                    sp.getDescription(),
                    sp.getTransactionPlace(),
                    Optional.of(sp.isNegotiable()),
                    Optional.empty(),
                    sp.getCreatedAt()
            );
        }
        else if (product instanceof SharingProduct){
            SharingProduct sp = (SharingProduct) product;
            return new ProductFindAllDto(
                    "Sharing",
                    sp.getTitle(),
                    sp.getSeller(),
                    Optional.empty(),
                    sp.getDescription(),
                    sp.getTransactionPlace(),
                    Optional.empty(),
                    Optional.of(sp.getSharingEvent()),
                    sp.getCreatedAt()
            );
        }
        else {
            return new ProductFindAllDto(
                    "Unknown",
                    product.getTitle(),
                    product.getSeller(),
                    Optional.empty(),
                    product.getDescription(),
                    product.getTransactionPlace(),
                    Optional.empty(),
                    Optional.empty(),
                    product.getCreatedAt()
            );
        }
    }
}

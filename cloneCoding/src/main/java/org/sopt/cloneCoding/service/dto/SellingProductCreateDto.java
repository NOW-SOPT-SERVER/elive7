package org.sopt.cloneCoding.service.dto;

import lombok.Getter;
import org.sopt.cloneCoding.domain.TransactionPlace;

@Getter
public class SellingProductCreateDto extends ProductCreateDto {
    private Double price;
    private Boolean negotiable;

    public SellingProductCreateDto(String title, Long sellerId, Double price, String description,
                                   TransactionPlace transactionPlace,
                                   Boolean negotiable) {
        super(title, sellerId, description, transactionPlace);
        this.price = price;
        this.negotiable = negotiable;
    }
}

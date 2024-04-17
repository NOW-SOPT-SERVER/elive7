package org.sopt.cloneCoding.service.dto;

import lombok.Getter;

@Getter
public class SellingProductCreateDto extends ProductCreateDto {
    private Double price;
    private Boolean negotiable;

    public SellingProductCreateDto(String title, Long sellerId, Double price, String description,
                                   String transactionPlace,
                                   Boolean negotiable) {
        super(title, sellerId, description, transactionPlace);
        this.price = price;
        this.negotiable = negotiable;
    }
}

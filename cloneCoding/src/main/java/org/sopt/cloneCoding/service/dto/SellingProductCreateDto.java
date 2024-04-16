package org.sopt.cloneCoding.service.dto;

import lombok.Getter;

@Getter
public class SellingProductCreateDto extends ProductCreateDto {
    private Boolean negotiable;

    public SellingProductCreateDto(String title, Long sellerId, String description,
                                   String transactionPlace,
                                   Boolean negotiable) {
        super(title, sellerId, 0.0, description, transactionPlace);
        this.negotiable = negotiable;
    }
}

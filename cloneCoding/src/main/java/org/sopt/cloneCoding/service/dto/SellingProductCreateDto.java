package org.sopt.cloneCoding.service.dto;

import lombok.Getter;
import org.sopt.cloneCoding.domain.TransactionPlace;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class SellingProductCreateDto extends ProductCreateDto {
    private Double price;
    private Boolean negotiable;

    public SellingProductCreateDto(String title, Long sellerId, Double price, String description,
                                   TransactionPlace transactionPlace,
                                   Boolean negotiable,
                                   MultipartFile image) {
        super(title, sellerId, description, transactionPlace, image);
        this.price = price;
        this.negotiable = negotiable;
    }
}

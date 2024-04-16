package org.sopt.cloneCoding.service.dto;

import lombok.Getter;

@Getter
public class SharingProductCreateDto extends ProductCreateDto {
    private Boolean sharingEvent;

    public SharingProductCreateDto(String title, Long sellerId, Double price, String description,
                                   String transactionPlace,
                                   Boolean sharingEvent) {
        super(title, sellerId, price, description, transactionPlace);
        this.sharingEvent = sharingEvent;
    }
}

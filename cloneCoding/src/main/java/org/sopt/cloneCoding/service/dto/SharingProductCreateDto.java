package org.sopt.cloneCoding.service.dto;

import lombok.Getter;

@Getter
public class SharingProductCreateDto extends ProductCreateDto {
    private Boolean sharingEvent;

    public SharingProductCreateDto(String title, Long sellerId, String description,
                                   String transactionPlace,
                                   Boolean sharingEvent) {
        super(title, sellerId, description, transactionPlace);
        this.sharingEvent = sharingEvent;
    }
}

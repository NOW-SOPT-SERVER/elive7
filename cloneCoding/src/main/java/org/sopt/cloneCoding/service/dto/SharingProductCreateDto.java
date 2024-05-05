package org.sopt.cloneCoding.service.dto;

import lombok.Getter;
import org.sopt.cloneCoding.domain.TransactionPlace;

@Getter
public class SharingProductCreateDto extends ProductCreateDto {
    private Boolean sharingEvent;

    public SharingProductCreateDto(String title, Long sellerId, String description,
                                   TransactionPlace transactionPlace,
                                   Boolean sharingEvent) {
        super(title, sellerId, description, transactionPlace);
        this.sharingEvent = sharingEvent;
    }
}

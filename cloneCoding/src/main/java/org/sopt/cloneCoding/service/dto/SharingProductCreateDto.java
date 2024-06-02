package org.sopt.cloneCoding.service.dto;

import lombok.Getter;
import org.sopt.cloneCoding.domain.TransactionPlace;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class SharingProductCreateDto extends ProductCreateDto {
    private Boolean sharingEvent;

    public SharingProductCreateDto(String title, Long sellerId, String description,
                                   TransactionPlace transactionPlace,
                                   Boolean sharingEvent,
                                   MultipartFile image) {
        super(title, sellerId, description, transactionPlace, image);
        this.sharingEvent = sharingEvent;
    }
}

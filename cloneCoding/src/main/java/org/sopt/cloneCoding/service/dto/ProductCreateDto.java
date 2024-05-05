package org.sopt.cloneCoding.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sopt.cloneCoding.domain.Member;
import org.sopt.cloneCoding.domain.TransactionPlace;

@Getter
@AllArgsConstructor
public class ProductCreateDto {
    private String title;
    private Long sellerId;
    private String description;
    private TransactionPlace transactionPlace;
}

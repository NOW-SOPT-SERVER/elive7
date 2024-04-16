package org.sopt.cloneCoding.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sopt.cloneCoding.domain.Member;

@Getter
@AllArgsConstructor
public class ProductCreateDto {
    private String title;
    private Long sellerId;
    private Double price;
    private String description;
    private String transactionPlace;
}

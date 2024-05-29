package org.sopt.cloneCoding.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {
    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED.value(), "멤버 등록이 완료되었습니다."),
    PRODUCT_CREATE_SUCCESS(HttpStatus.CREATED.value(), "상품 등록이 완료되었습니다."),
    LIKE_CREATE_SUCCESS(HttpStatus.CREATED.value(), "좋아요가 추가되었습니다."),
    RRODUCT_GET_SUCCESS(HttpStatus.OK.value(),"상품 목록 조회가 완료되었습니다.")
    ;
    private final int status;
    private final String message;
}

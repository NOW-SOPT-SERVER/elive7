package org.sopt.cloneCoding.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 유저가 존재하지 않습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 상품이 존재하지 않습니다."),
    DUPLICATE_LIKE(HttpStatus.CONFLICT.value(), "이미 좋아요를 누른 상품입니다."),
    ARGUMENT_MISMATCH(HttpStatus.BAD_REQUEST.value(), "파라미터 매핑에 실패했습니다."),
    ;
    private final int status;
    private final String message;
}

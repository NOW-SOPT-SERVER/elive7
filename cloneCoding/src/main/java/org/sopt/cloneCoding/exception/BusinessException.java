package org.sopt.cloneCoding.exception;

import lombok.Getter;
import org.sopt.cloneCoding.common.dto.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException{
    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}

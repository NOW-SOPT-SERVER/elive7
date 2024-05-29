package org.sopt.cloneCoding.exception;

import org.sopt.cloneCoding.common.dto.ErrorMessage;

public class NotFoundException extends BusinessException{
    public NotFoundException(ErrorMessage errorMessage) {super(errorMessage);}
}

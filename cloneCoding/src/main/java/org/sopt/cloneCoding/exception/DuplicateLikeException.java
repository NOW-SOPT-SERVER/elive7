package org.sopt.cloneCoding.exception;

import org.sopt.cloneCoding.common.dto.ErrorMessage;

public class DuplicateLikeException extends BusinessException{
    public DuplicateLikeException(ErrorMessage errorMessage){ super(errorMessage);}
}

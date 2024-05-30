package opt.sopt.practice.exception;

import opt.sopt.practice.common.dto.ErrorMessage;

public class AccessTokenExpiredException extends BusinessException {
    public AccessTokenExpiredException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

package opt.sopt.practice.exception;

import opt.sopt.practice.common.dto.ErrorMessage;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
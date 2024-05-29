package opt.sopt.practice.exception;

import opt.sopt.practice.common.dto.ErrorMessage;

public class UnauthorizedActionException extends BusinessException {
    public UnauthorizedActionException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

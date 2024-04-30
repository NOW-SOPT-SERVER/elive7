package opt.sopt.practice.exception;

import lombok.Getter;
import opt.sopt.practice.common.dto.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}

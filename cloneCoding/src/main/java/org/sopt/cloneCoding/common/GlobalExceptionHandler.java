package org.sopt.cloneCoding.common;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.util.Objects;
import org.sopt.cloneCoding.common.dto.ErrorMessage;
import org.sopt.cloneCoding.common.dto.ErrorResponse;
import org.sopt.cloneCoding.exception.DuplicateLikeException;
import org.sopt.cloneCoding.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of(HttpStatus.BAD_REQUEST.value(),
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(ErrorMessage.ARGUMENT_MISMATCH));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        if (e.getCause() instanceof MismatchedInputException mismatchedInputException) {
            return ResponseEntity.badRequest()
                    .body((new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            mismatchedInputException.getPath().get(0).getFieldName()+ " 필드의 값이 잘못되었습니다."
                    )));
        }
        else {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "확인할 수 없는 형태의 데이터가 들어왔습니다."));
        }
    }
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(e.getErrorMessage()));
    }

    @ExceptionHandler(DuplicateLikeException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicateLikeException(DuplicateLikeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of(e.getErrorMessage()));
    }
}

package org.sopt.cloneCoding.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public record SuccessStatusResponse<T>(int status, String message, Optional<T> data) {
    public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage) {
        return new SuccessStatusResponse(successMessage.getStatus(), successMessage.getMessage(), Optional.empty());
    }

    public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage, T data) {
        return new SuccessStatusResponse(successMessage.getStatus(), successMessage.getMessage(), Optional.of(data));
    }
}

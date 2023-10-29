package com.ifba.educampo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MonthlyFeeException extends RuntimeException {
    private final List<ErrorType> errors;

    public MonthlyFeeException(String message, List<ErrorType> errorList) {
        super(message);
        this.errors = errorList;
    }
}

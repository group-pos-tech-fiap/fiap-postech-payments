package com.fiap.fastfood.payments.common.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CustomException extends Exception {

    private final String code;
    private List<CustomError> errors;

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
    }
}

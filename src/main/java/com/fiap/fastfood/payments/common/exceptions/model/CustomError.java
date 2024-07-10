package com.fiap.fastfood.payments.common.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomError {

    private String message;
    private String field;
    private Object attemptedValue;

}

package com.fiap.fastfood.payments.common.exceptions.custom;

import com.fiap.fastfood.payments.common.exceptions.model.CustomException;

public class EntityNotFoundException extends CustomException {
    public EntityNotFoundException(String code, String message) {
        super(code, message);
    }
}

package com.fiap.fastfood.payments.common.builders;

import com.fiap.fastfood.payments.common.dto.request.CheckoutRequest;
import com.fiap.fastfood.payments.common.dto.response.CheckoutResponse;
import com.fiap.fastfood.payments.core.entity.Checkout;
import com.fiap.fastfood.payments.external.orm.CheckoutORM;

import java.time.LocalDateTime;

public class CheckoutBuilder {

    public static Checkout fromRequestToDomain(CheckoutRequest request) {
        return Checkout.builder()
                .status("In Progress")
                .orderId(request.getOrderId())
                .value(request.getValue())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CheckoutResponse fromDomainToResponse(Checkout checkout) {
        return CheckoutResponse.builder()
                .id(checkout.getId())
                .status(checkout.getStatus())
                .orderId(checkout.getOrderId())
                .value(checkout.getValue())
                .createAt(checkout.getCreatedAt())
                .nsu(checkout.getId())
                .build();
    }

    public static Checkout fromOrmToDomain(CheckoutORM orm) {
        return Checkout.builder()
                .id(orm.getId())
                .status(orm.getStatus())
                .orderId(orm.getOrderId())
                .value(orm.getValue())
                .build();
    }

    public static CheckoutORM fromDomainToOrm(Checkout checkout) {
        return CheckoutORM.builder()
                .id(checkout.getId())
                .status(checkout.getStatus())
                .orderId(checkout.getOrderId())
                .value(checkout.getValue())
                .createdAt(checkout.getCreatedAt())
                .build();
    }
}

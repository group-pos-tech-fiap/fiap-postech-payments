package com.fiap.fastfood.payments.common.interfaces.usecase;

import com.fiap.fastfood.payments.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.payments.core.entity.Checkout;

import java.util.List;

public interface CheckoutUseCase {

    void submit(Checkout checkout, CheckoutGateway checkoutGateway);

    List<Checkout> findAll(CheckoutGateway checkoutGateway);
}

package com.fiap.fastfood.payments.core.usecase;

import com.fiap.fastfood.payments.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.payments.common.interfaces.usecase.CheckoutUseCase;
import com.fiap.fastfood.payments.core.entity.Checkout;

import java.util.List;

public class CheckoutUseCaseImpl implements CheckoutUseCase {
    @Override
    public void submit(Checkout checkout, CheckoutGateway checkoutGateway) {
        checkoutGateway.save(checkout);
    }

    @Override
    public List<Checkout> findAll(CheckoutGateway checkoutGateway) {
        return checkoutGateway.findAll();
    }
}

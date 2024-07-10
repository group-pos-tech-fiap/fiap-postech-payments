package com.fiap.fastfood.payments.common.interfaces.gateways;

import com.fiap.fastfood.payments.core.entity.Checkout;

import java.util.List;

public interface CheckoutGateway {

    void save(Checkout checkout);

    List<Checkout> findAll();
}

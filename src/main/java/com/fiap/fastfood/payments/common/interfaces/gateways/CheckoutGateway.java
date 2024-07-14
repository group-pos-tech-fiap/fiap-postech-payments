package com.fiap.fastfood.payments.common.interfaces.gateways;

import com.fiap.fastfood.payments.core.entity.Checkout;
import com.fiap.fastfood.payments.external.orm.CheckoutORM;

import java.util.List;

public interface CheckoutGateway {

    CheckoutORM save(Checkout checkout);

    List<Checkout> findAll();
}

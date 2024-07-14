package com.fiap.fastfood.payments.common.builders;

import com.fiap.fastfood.payments.common.dto.request.CheckoutRequest;
import com.fiap.fastfood.payments.common.dto.response.CheckoutResponse;
import com.fiap.fastfood.payments.core.entity.Checkout;
import com.fiap.fastfood.payments.external.orm.CheckoutORM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
class CheckoutBuilderTest {

    Checkout checkout;
    CheckoutORM checkoutORM;

    @BeforeEach
    void setup() {
        checkout = createMockCheckout();
        checkoutORM = createMockCheckoutORM();
    }

    @Test
    void validFromRequestToDomain() {
        var response = CheckoutBuilder.fromRequestToDomain(new CheckoutRequest("1010", BigDecimal.valueOf(12345)));

        assertThat(response.getOrderId()).isEqualTo(checkout.getOrderId());
        assertThat(response.getValue()).isEqualTo(checkout.getValue());
        assertThat(response.getStatus()).isEqualTo(checkout.getStatus());
        assertInstanceOf(Checkout.class, response);
    }

    @Test
    void validFromDomainToResponse() {
        var response = CheckoutBuilder.fromDomainToResponse(checkout);

        assertThat(response.getId()).isEqualTo(checkout.getId());
        assertThat(response.getValue()).isEqualTo(checkout.getValue());
        assertThat(response.getStatus()).isEqualTo(checkout.getStatus());
        assertThat(response.getOrderId()).isEqualTo(checkout.getOrderId());
        assertThat(response.getCreateAt()).isEqualTo(checkout.getCreatedAt());
        assertInstanceOf(CheckoutResponse.class, response);
    }

    @Test
    void validFromOrmToDomain() {

        var response = CheckoutBuilder.fromOrmToDomain(checkoutORM);

        assertThat(response.getId()).isEqualTo(checkoutORM.getId());
        assertThat(response.getValue()).isEqualTo(checkoutORM.getValue());
        assertThat(response.getStatus()).isEqualTo(checkoutORM.getStatus());
        assertThat(response.getOrderId()).isEqualTo(checkoutORM.getOrderId());
        assertInstanceOf(Checkout.class, response);
    }

    @Test
    void validDomainToOrm() {

        var response = CheckoutBuilder.fromDomainToOrm(checkout);

        assertThat(response.getId()).isEqualTo(checkout.getId());
        assertThat(response.getValue()).isEqualTo(checkout.getValue());
        assertThat(response.getStatus()).isEqualTo(checkout.getStatus());
        assertThat(response.getOrderId()).isEqualTo(checkout.getOrderId());
        assertInstanceOf(CheckoutORM.class, response);
    }

    private Checkout createMockCheckout() {
        return Checkout.builder()
                .id("abc")
                .orderId("1010")
                .value(BigDecimal.valueOf(12345))
                .status("In Progress")
                .createdAt(null)
                .build();
    }

    private CheckoutORM createMockCheckoutORM() {
        return CheckoutORM.builder()
                .id("abc")
                .orderId("1010")
                .value(BigDecimal.valueOf(12345))
                .status("In Progress")
                .createdAt(null)
                .build();
    }
}
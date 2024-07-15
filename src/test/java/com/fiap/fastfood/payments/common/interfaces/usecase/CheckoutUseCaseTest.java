package com.fiap.fastfood.payments.common.interfaces.usecase;

import com.fiap.fastfood.payments.common.builders.CheckoutBuilder;
import com.fiap.fastfood.payments.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.payments.core.entity.Checkout;
import com.fiap.fastfood.payments.core.usecase.CheckoutUseCaseImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckoutUseCaseTest {

    @Mock
    private CheckoutGateway checkoutGateway;

    @InjectMocks
    private CheckoutUseCaseImpl checkoutUseCase;

    private Checkout checkout;

    @BeforeEach
    void setup() {
        checkout = createMockCheckout();
    }

    @Test
    public void createCheckout() {
        var checkoutORM = CheckoutBuilder.fromDomainToOrm(checkout);
        when(checkoutGateway.save(CheckoutBuilder.fromOrmToDomain(checkoutORM))).thenReturn(checkoutORM);

        var saveCheckout = checkoutUseCase.submit(checkout, checkoutGateway);

        Assertions.assertThat(saveCheckout.getId()).isEqualTo(checkout.getId());
        Assertions.assertThat(saveCheckout.getOrderId()).isEqualTo(checkout.getOrderId());
        Assertions.assertThat(saveCheckout.getValue().compareTo(checkout.getValue()));
        Assertions.assertThat(saveCheckout.getStatus()).isEqualTo(checkout.getStatus());
        verify(checkoutGateway, times(1)).save(CheckoutBuilder.fromOrmToDomain(checkoutORM));
    }

    @Test
    public void listCheckout() {
        when(checkoutGateway.findAll()).thenReturn(List.of(checkout));

        var list = checkoutUseCase.findAll(checkoutGateway);

        Assertions.assertThat(list.size()).isEqualTo(1);
        verify(checkoutGateway, times(1)).findAll();
    }

    private Checkout createMockCheckout() {
        return Checkout.builder()
                .id("abc")
                .orderId("1010")
                .value(BigDecimal.valueOf(12345))
                .status("OK")
                .createdAt(null)
                .build();
    }
}
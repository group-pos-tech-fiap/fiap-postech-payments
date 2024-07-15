package com.fiap.fastfood.payments.common.interfaces.gateways;

import com.fiap.fastfood.payments.common.builders.CheckoutBuilder;
import com.fiap.fastfood.payments.common.interfaces.datasource.SpringDataJPACheckoutRepository;
import com.fiap.fastfood.payments.communication.gateways.CheckoutGatewayImpl;
import com.fiap.fastfood.payments.core.entity.Checkout;
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
class CheckoutGatewayTest {

    @Mock
    private SpringDataJPACheckoutRepository repository;

    @InjectMocks
    private CheckoutGatewayImpl checkoutGateway;

    private Checkout checkout;

    @BeforeEach
    void setup() {
        checkout = createMockCheckout();
    }

    @Test
    public void createCheckout() {
        var checkoutORM = CheckoutBuilder.fromDomainToOrm(checkout);
        when(repository.save(checkoutORM)).thenReturn(checkoutORM);

        var saveCheckout = checkoutGateway.save(checkout);

        Assertions.assertThat(saveCheckout.getId()).isEqualTo(checkout.getId());
        Assertions.assertThat(saveCheckout.getOrderId()).isEqualTo(checkout.getOrderId());
        Assertions.assertThat(saveCheckout.getValue().compareTo(checkout.getValue()));
        Assertions.assertThat(saveCheckout.getStatus()).isEqualTo(checkout.getStatus());
        verify(repository, times(1)).save(checkoutORM);
    }

    @Test
    public void listCheckout() {
        var checkoutORM = CheckoutBuilder.fromDomainToOrm(checkout);
        when(repository.findAllByOrderByCreatedAtAsc()).thenReturn(List.of(checkoutORM));

        var list = checkoutGateway.findAll();

        Assertions.assertThat(list.size()).isEqualTo(1);
        verify(repository, times(1)).findAllByOrderByCreatedAtAsc();
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
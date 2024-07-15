package com.fiap.fastfood.payments.common.beans;

import com.fiap.fastfood.payments.common.interfaces.datasource.SpringDataJPACheckoutRepository;
import com.fiap.fastfood.payments.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.payments.communication.gateways.CheckoutGatewayImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GatewayBeanDeclarationTest {

    @Mock
    private SpringDataJPACheckoutRepository repository;

    @Test
    void testCheckoutGatewayBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(SpringDataJPACheckoutRepository.class, () -> repository);
        context.register(GatewayBeanDeclaration.class);
        context.refresh();

        CheckoutGateway checkoutGateway = context.getBean(CheckoutGateway.class);

        assertNotNull(checkoutGateway);
        assertTrue(checkoutGateway instanceof CheckoutGatewayImpl);
    }

}
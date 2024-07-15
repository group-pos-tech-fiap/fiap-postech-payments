package com.fiap.fastfood.payments.common.beans;

import com.fiap.fastfood.payments.common.interfaces.usecase.CheckoutUseCase;
import com.fiap.fastfood.payments.core.usecase.CheckoutUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UseCaseBeansDeclarationTest {

    @Test
    void testCheckoutUseCaseBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(UseCaseBeansDeclaration.class);
        context.refresh();

        CheckoutUseCase checkoutUseCase = context.getBean(CheckoutUseCase.class);

        assertNotNull(checkoutUseCase);
        assertTrue(checkoutUseCase instanceof CheckoutUseCaseImpl);
    }
}
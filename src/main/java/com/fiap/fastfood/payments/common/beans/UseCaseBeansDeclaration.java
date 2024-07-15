package com.fiap.fastfood.payments.common.beans;

import com.fiap.fastfood.payments.common.interfaces.usecase.CheckoutUseCase;
import com.fiap.fastfood.payments.core.usecase.CheckoutUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeansDeclaration {
    @Bean
    public CheckoutUseCase checkoutUseCase() {
        return new CheckoutUseCaseImpl();
    }
}

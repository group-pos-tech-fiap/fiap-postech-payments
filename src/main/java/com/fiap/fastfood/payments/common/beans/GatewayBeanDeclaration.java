package com.fiap.fastfood.payments.common.beans;

import com.fiap.fastfood.payments.common.interfaces.datasource.SpringDataJPACheckoutRepository;
import com.fiap.fastfood.payments.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.payments.communication.gateways.CheckoutGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBeanDeclaration {
    @Bean
    public CheckoutGateway checkoutGateway(SpringDataJPACheckoutRepository repository) {
        return new CheckoutGatewayImpl(repository);
    }
}

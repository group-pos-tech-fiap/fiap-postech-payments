package com.fiap.fastfood.payments.communication.consumers;

import com.fiap.fastfood.payments.common.builders.CheckoutBuilder;
import com.fiap.fastfood.payments.common.dto.request.CheckoutRequest;
import com.fiap.fastfood.payments.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.payments.common.interfaces.usecase.CheckoutUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckoutConsumer {

    private final CheckoutGateway gateway;
    private final CheckoutUseCase useCase;

    private static final String
    SHOP_TOPIC_NAME = "fiap-fastfood-checkout";

    public CheckoutConsumer(@Qualifier("checkoutGateway") CheckoutGateway gateway, CheckoutUseCase useCase) {
        this.gateway = gateway;
        this.useCase = useCase;
    }

    @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
    public void listenShopTopic(CheckoutRequest message) {
        try {
            final var checkoutReq = CheckoutBuilder.fromRequestToDomain(message);
            CheckoutBuilder.fromDomainToResponse(useCase.submit(checkoutReq, gateway));
        } catch(Exception e) {
            message.getOrderId();
        }
    }
}

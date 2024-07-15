package com.fiap.fastfood.payments.communication.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.fastfood.payments.common.dto.request.CheckoutRequest;
import com.fiap.fastfood.payments.common.interfaces.gateways.CheckoutGateway;
import com.fiap.fastfood.payments.common.interfaces.usecase.CheckoutUseCase;
import com.fiap.fastfood.payments.core.entity.Checkout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class CheckoutControllerTest {

    @Mock
    private CheckoutUseCase checkoutUseCase;

    @Mock
    private CheckoutGateway checkoutGateway;

    @InjectMocks
    private CheckoutController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void createCheckout() throws Exception {
        var checkout = createMockCheckout();
        var request = new CheckoutRequest(checkout.getOrderId(), checkout.getValue());

        when(checkoutUseCase.submit(checkout, checkoutGateway)).thenReturn(checkout);

        mockMvc.perform(post("/perform-payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(checkoutUseCase, times(1)).submit(checkout, checkoutGateway);
    }

    @Test
    void listCheckout() throws Exception {
        var checkout = createMockCheckout();

        when(checkoutUseCase.findAll(checkoutGateway)).thenReturn(Collections.singletonList(checkout));

        mockMvc.perform(get("/perform-payment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(checkoutUseCase, times(1)).findAll(checkoutGateway);
    }

    private Checkout createMockCheckout() {
        return Checkout.builder()
                .orderId("1010")
                .value(BigDecimal.valueOf(12345))
                .status("In Progress")
                .createdAt(null)
                .build();
    }
}
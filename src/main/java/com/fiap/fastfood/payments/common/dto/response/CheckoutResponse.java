package com.fiap.fastfood.payments.common.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class CheckoutResponse {
    private String id;
    private String orderId;
    private BigDecimal value;
    private String status;
    private LocalDateTime createAt;
}

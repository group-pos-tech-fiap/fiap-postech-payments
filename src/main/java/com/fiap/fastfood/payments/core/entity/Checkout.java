package com.fiap.fastfood.payments.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {
    private String id;
    private String orderId;
    private BigDecimal value;
    private String status;
    private LocalDateTime createdAt;
}

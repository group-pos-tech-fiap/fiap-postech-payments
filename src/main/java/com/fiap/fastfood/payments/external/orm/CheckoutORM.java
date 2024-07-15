package com.fiap.fastfood.payments.external.orm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutORM {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
    private String orderId;
    private BigDecimal value;
    private String status;
    private LocalDateTime createdAt;
}
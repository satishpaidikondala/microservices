package com.ecommerce.orderservice.dto;

import java.math.BigDecimal;
import java.util.UUID;
import com.ecommerce.orderservice.entity.OrderStatus;

public record OrderResponse(
    UUID orderNumber,
    OrderStatus status,
    BigDecimal totalAmount
) {}

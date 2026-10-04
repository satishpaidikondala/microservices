package com.ecommerce.orderservice.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
    @NotBlank(message = "Product ID is required") String productId,
    String skuCode,
    @NotNull(message = "Price is required") BigDecimal price,
    @NotNull(message = "Quantity is required") @Min(1) Integer quantity
) {}

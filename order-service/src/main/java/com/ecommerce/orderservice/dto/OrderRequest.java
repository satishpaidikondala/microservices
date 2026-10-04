package com.ecommerce.orderservice.dto;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record OrderRequest(
    @NotEmpty(message = "Order must have at least one item") 
    @Valid 
    List<OrderItemRequest> orderItems
) {}

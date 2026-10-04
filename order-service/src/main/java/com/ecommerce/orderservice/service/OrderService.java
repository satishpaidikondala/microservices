package com.ecommerce.orderservice.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.entity.OrderStatus;
import com.ecommerce.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        // 1. Create a new Order Entity
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID());
        order.setStatus(OrderStatus.CREATED);
        
        // 2. Map DTOs to Entities and maintain bidirectional relationship
        List<OrderItem> orderItems = orderRequest.orderItems().stream()
                .map(itemRequest -> {
                    OrderItem item = new OrderItem();
                    item.setProductId(itemRequest.productId());
                    item.setSkuCode(itemRequest.skuCode());
                    item.setPrice(itemRequest.price());
                    item.setQuantity(itemRequest.quantity());
                    item.setOrder(order); // Important: Link child to parent!
                    return item;
                }).toList();

        order.setOrderItems(orderItems);

        // 3. Calculate Total Amount
        BigDecimal totalAmount = orderItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        order.setTotalAmount(totalAmount);

        // 4. Save to Database
        Order savedOrder = orderRepository.save(order);

        // 5. Map back to Response DTO
        return new OrderResponse(
                savedOrder.getOrderNumber(),
                savedOrder.getStatus(),
                savedOrder.getTotalAmount()
        );
    }
}

package com.qeemaTask.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
    private int id;
    private int orderId;
    private double price;
    private int productId;
    private int quantity;
}

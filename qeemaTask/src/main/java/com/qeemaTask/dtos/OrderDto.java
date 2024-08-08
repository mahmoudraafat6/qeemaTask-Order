package com.qeemaTask.dtos;

import com.qeemaTask.entities.OrderItemEntity;
import com.qeemaTask.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int id ;
    private LocalDateTime createdAt;
   private List<ProductEntity> productList;
}
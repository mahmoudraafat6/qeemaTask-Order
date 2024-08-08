package com.qeemaTask.mappers;


import com.qeemaTask.dtos.OrderDto;
import com.qeemaTask.dtos.OrderItemDto;
import com.qeemaTask.dtos.ProductDto;
import com.qeemaTask.entities.OrderEntity;
import com.qeemaTask.entities.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDto convertToDto(OrderItemEntity OrderItemEntity);

    default  OrderItemEntity convertToEntity(OrderItemDto orderItemDto){
        return OrderItemEntity.builder()
                .quantity(orderItemDto.getQuantity())
                .orderId(orderItemDto.getId())
                .productId(orderItemDto.getProductId())
                .build();
    }


}

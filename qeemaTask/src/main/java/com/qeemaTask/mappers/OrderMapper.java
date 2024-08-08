package com.qeemaTask.mappers;

import com.qeemaTask.dtos.OrderDto;
import com.qeemaTask.dtos.ProductDto;
import com.qeemaTask.entities.OrderEntity;
import com.qeemaTask.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    default OrderDto convertToDto(OrderEntity orderEntity){
        return OrderDto.builder()
                .id(orderEntity.getId())
                .createdAt(orderEntity.getCreatedAt())
                .build();
    }
    default  OrderEntity convertToEntity(OrderDto orderDto){
        return OrderEntity
                .builder()
                .id(orderDto.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}

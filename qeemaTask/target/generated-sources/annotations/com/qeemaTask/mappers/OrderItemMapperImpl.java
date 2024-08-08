package com.qeemaTask.mappers;

import com.qeemaTask.dtos.OrderItemDto;
import com.qeemaTask.dtos.OrderItemDto.OrderItemDtoBuilder;
import com.qeemaTask.entities.OrderItemEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-08T11:29:31+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDto convertToDto(OrderItemEntity OrderItemEntity) {
        if ( OrderItemEntity == null ) {
            return null;
        }

        OrderItemDtoBuilder orderItemDto = OrderItemDto.builder();

        orderItemDto.id( OrderItemEntity.getId() );
        orderItemDto.orderId( OrderItemEntity.getOrderId() );
        orderItemDto.price( OrderItemEntity.getPrice() );
        orderItemDto.productId( OrderItemEntity.getProductId() );
        orderItemDto.quantity( OrderItemEntity.getQuantity() );

        return orderItemDto.build();
    }
}

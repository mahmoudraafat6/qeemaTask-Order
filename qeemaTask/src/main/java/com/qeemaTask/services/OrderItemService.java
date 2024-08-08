package com.qeemaTask.services;

import com.qeemaTask.dtos.OrderDto;
import com.qeemaTask.dtos.OrderItemDto;
import com.qeemaTask.entities.OrderEntity;
import com.qeemaTask.entities.OrderItemEntity;
import com.qeemaTask.entities.ProductEntity;
import com.qeemaTask.exceptions.OrderItemServiceException;
import com.qeemaTask.exceptions.OrderServiceException;
import com.qeemaTask.mappers.OrderItemMapper;
import com.qeemaTask.mappers.OrderMapper;
import com.qeemaTask.repositories.OrderItemRepository;
import com.qeemaTask.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;


@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    private Logger logger = LoggerFactory.getLogger(OrderItemService.class);

    public int createItem(int orderId ,OrderDto orderDto){
      int itemSize =0;
        try {
            logger.info("Start Handle Order Items");
            List<OrderItemEntity> orderItemEntityList = orderDto.getProductList().stream()
                    .map(product -> createOrderItem(orderId, product))
                    .collect(Collectors.toList());

            itemSize =orderItemRepository.saveAll(orderItemEntityList).size();
            logger.info("Finish Handle Order Items");
        }catch (Exception exception){
            logger.error("Error while try to add order Items");
            throw new OrderItemServiceException("Failed to order Item ", exception);
        }
        return itemSize;
        }

        public List<OrderItemDto> getAllItems( ){
            return orderItemRepository.findAll().stream().map(this::mapOrderitemtodto).collect(Collectors.toList());
        }

        public List<ProductEntity> getItem(int orderId){
            return  productRepository.getItem(orderId);

        }

    private OrderItemEntity createOrderItem(int id , ProductEntity productEntity){
        productService.validateProductEntity(productEntity);
        return OrderItemEntity.builder()
                .orderId(id)
                .productId(productEntity.getId())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .build();
    }


    public OrderItemDto mapOrderitemtodto(OrderItemEntity orderEntity) {
        return OrderItemMapper.INSTANCE.convertToDto(orderEntity);
    }

}

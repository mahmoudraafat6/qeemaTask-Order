package com.qeemaTask.services;

import com.qeemaTask.exceptions.OrderServiceException;
import com.qeemaTask.dtos.OrderDto;
import com.qeemaTask.entities.OrderEntity;
import com.qeemaTask.mappers.OrderMapper;
import com.qeemaTask.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository ;

    @Autowired
    private OrderItemService orderItemService;


    private Logger logger = LoggerFactory.getLogger(OrderService.class);


    public Integer createOrder(OrderDto orderDto) {
        Integer id =null;
        try {
            OrderEntity orderEntity = mapOrderToEntity(orderDto);
            id = orderRepository.save(orderEntity).getId();
            logger.info("Create order with Id" + id);
            orderItemService.createItem(id , orderDto);

        }catch (Exception e){
            logger.error("Error while try to create Order"+ e.getMessage());
            throw new OrderServiceException("Failed to create order", e);
        }
        return id;
    }

    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtoList = null;
        try {
            logger.info("Start get All orders");
            orderDtoList = orderRepository.findAll().stream()
                    .map(orderEntity -> {
                        OrderDto orderDto = mapOrdertodto(orderEntity);
                        orderDto.setProductList(orderItemService.getItem(orderDto.getId()));
                        return orderDto;
                    })
                    .collect(Collectors.toList());

            logger.info("Finish get All orders");

        } catch (Exception e) {
            logger.error("Error while try to get All orders" + e.getMessage());
            throw new OrderServiceException("Failed to get all orders", e);
        }
        return orderDtoList;
    }

    private OrderEntity mapOrderToEntity(OrderDto orderDto) {
        return OrderMapper.INSTANCE.convertToEntity(orderDto);
    }
    public OrderDto mapOrdertodto(OrderEntity orderEntity) {
        return OrderMapper.INSTANCE.convertToDto(orderEntity);
    }

}

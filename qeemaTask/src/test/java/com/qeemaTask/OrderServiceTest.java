package com.qeemaTask;

import com.qeemaTask.dtos.OrderDto;
import com.qeemaTask.entities.OrderEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.qeemaTask.exceptions.OrderServiceException;
import com.qeemaTask.repositories.OrderRepository;
import com.qeemaTask.services.OrderItemService;
import com.qeemaTask.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder(){

        OrderDto orderDto = new OrderDto();
        OrderEntity orderEntity = OrderEntity.builder()
                .id(1)
                .build();

        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        Integer orderId = orderService.createOrder(orderDto);


        assertNotNull(orderId);
        assertEquals(1, orderId);

    }

    @Test
    public void testGetAllOrders(){
            List<OrderEntity> orderEntities = new ArrayList<>();
            OrderEntity orderEntity = OrderEntity.builder()
                    .id(1)
                    .build();

            orderEntities.add(orderEntity);

            when(orderRepository.findAll()).thenReturn(orderEntities);
            when(orderItemService.getItem(anyInt())).thenReturn(new ArrayList<>());


            List<OrderDto> orderDtos = orderService.getAllOrders();

            assertNotNull(orderDtos);
            assertEquals(1, orderDtos.size());
    }

    @Test
    void testCreateOrderFailure() {
        OrderDto orderDto = new OrderDto();

        when(orderRepository.save(any(OrderEntity.class))).thenThrow(new RuntimeException("Save failed"));

        Exception exception = Assertions.assertThrows(OrderServiceException.class, () -> {
            orderService.createOrder(orderDto);
        });

        assertEquals("Failed to create order", exception.getMessage());

    }

    @Test
    void testGetAllOrdersFailure() {
        when(orderRepository.findAll()).thenThrow(new RuntimeException("Find all failed"));

        Exception exception = Assertions.assertThrows(OrderServiceException.class, () -> {
            orderService.getAllOrders();
        });

        assertEquals("Failed to get all orders", exception.getMessage());

    }


}

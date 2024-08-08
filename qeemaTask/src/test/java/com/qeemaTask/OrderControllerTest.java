package com.qeemaTask;

import com.qeemaTask.controllers.OrderController;
import com.qeemaTask.dtos.OrderDto;
import com.qeemaTask.entities.ProductEntity;
import com.qeemaTask.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private OrderDto orderDto;
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productEntity = ProductEntity.builder().id(1).quantity(2).price(100.0).build();

        List<ProductEntity> productList = new ArrayList<>();
        productList.add(productEntity);
        orderDto = OrderDto.builder().productList(productList).build();

    }

    @Test
    void testGetAllOrders() {
        when(orderService.getAllOrders()).thenReturn(Collections.singletonList(orderDto));

        List<OrderDto> orders = orderController.getAllOrders();

        verify(orderService).getAllOrders();

        assertNotNull(orders);
        assertEquals(1, orders.size());
        assertEquals(orderDto, orders.get(0));
    }

    @Test
    void testCreateOrder() {
        when(orderService.createOrder(any(OrderDto.class))).thenReturn(1);
        String response = orderController.createOrder(orderDto);
        verify(orderService).createOrder(orderDto);
        assertEquals("Order created Successfully", response);
    }

    @Test
    void testCreateOrderFailure() {

        doThrow(new RuntimeException("Service error")).when(orderService).createOrder(any(OrderDto.class));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            orderController.createOrder(orderDto);
        });

        verify(orderService).createOrder(orderDto);

        assertEquals("Service error", thrown.getMessage());
    }
}

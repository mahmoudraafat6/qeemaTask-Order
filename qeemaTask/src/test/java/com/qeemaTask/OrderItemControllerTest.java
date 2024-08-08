package com.qeemaTask;

import com.qeemaTask.controllers.OrderItemController;
import com.qeemaTask.dtos.OrderItemDto;
import com.qeemaTask.services.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrderItemControllerTest {


    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private OrderItemController orderItemController;

    private OrderItemDto orderItemDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderItemDto = OrderItemDto.builder()
                .id(1)
                .quantity(2)
                .price(50.0)
                .build();
    }

    @Test
    void testGetAllOrderItems() {
        when(orderItemService.getAllItems()).thenReturn(Collections.singletonList(orderItemDto));

        List<OrderItemDto> orderItems = orderItemController.getAllOrderItems();


        verify(orderItemService).getAllItems();
        assertNotNull(orderItems);
        assertEquals(1, orderItems.size());
        assertEquals(orderItemDto, orderItems.get(0));
    }

    @Test
    void testGetAllOrderItemsEmpty() {
        when(orderItemService.getAllItems()).thenReturn(Collections.emptyList());

        List<OrderItemDto> orderItems = orderItemController.getAllOrderItems();

        verify(orderItemService).getAllItems();

        assertNotNull(orderItems);
        assertTrue(orderItems.isEmpty());
    }
}

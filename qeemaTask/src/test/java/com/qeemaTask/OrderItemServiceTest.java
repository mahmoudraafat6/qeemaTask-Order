package com.qeemaTask;

        import com.qeemaTask.dtos.OrderDto;
        import com.qeemaTask.dtos.OrderItemDto;
        import com.qeemaTask.entities.OrderItemEntity;
        import com.qeemaTask.entities.ProductEntity;
        import com.qeemaTask.exceptions.OrderItemServiceException;
        import com.qeemaTask.repositories.OrderItemRepository;
        import com.qeemaTask.repositories.ProductRepository;
        import com.qeemaTask.services.OrderItemService;
        import com.qeemaTask.services.ProductService;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;
        import org.mockito.junit.jupiter.MockitoExtension;
        import org.slf4j.Logger;
        import org.springframework.boot.test.context.SpringBootTest;

        import java.util.ArrayList;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.ArgumentMatchers.anyInt;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderItemService orderItemService;

    private OrderDto orderDto;
    private ProductEntity productEntity;
    private OrderItemEntity orderItemEntity;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productEntity = ProductEntity.builder()
                .id(1)
                .quantity(2)
                .price(100.0)
                .build();

        List<ProductEntity> productList = new ArrayList<>();
        productList.add(productEntity);
        orderDto = OrderDto.builder()
                .productList(productList)
                .build();

        orderItemEntity = OrderItemEntity.builder()
                .orderId(1)
                .productId(productEntity.getId())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .build();
    }

    @Test
    void testCreateItem_Success() {

        when(orderItemRepository.saveAll(anyList())).thenReturn(List.of(orderItemEntity));

        int result = orderItemService.createItem(1, orderDto);

        assertEquals(1, result);


    }

    @Test
    void testCreateItemFailure() {
        when(orderItemRepository.saveAll(anyList())).thenThrow(new RuntimeException());

        assertThrows(OrderItemServiceException.class, () -> orderItemService.createItem(1, orderDto));

    }

    @Test
    void testGetAllItems() {

        when(orderItemRepository.findAll()).thenReturn(List.of(orderItemEntity));

        List<OrderItemDto> result = orderItemService.getAllItems();

        assertEquals(1, result.size());
        assertEquals(orderItemEntity.getOrderId(), result.get(0).getOrderId());

    }

    @Test
    void testGetItem() {

        when(productRepository.getItem(1)).thenReturn(List.of(productEntity));

        List<ProductEntity> result = orderItemService.getItem(1);

        assertEquals(1, result.size());
        assertEquals(productEntity.getId(), result.get(0).getId());
    }


}


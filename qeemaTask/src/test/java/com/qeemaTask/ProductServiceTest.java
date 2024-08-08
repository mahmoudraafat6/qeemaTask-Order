package com.qeemaTask;

import com.qeemaTask.dtos.ProductDto;
import com.qeemaTask.entities.ProductEntity;
import com.qeemaTask.exceptions.ProductServiceException;
import com.qeemaTask.mappers.ProductMapper;
import com.qeemaTask.repositories.ProductRepository;
import com.qeemaTask.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    ProductDto productDto ;
    ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productEntity = ProductEntity.builder()
                .id(1)
                .quantity(2)
                .price(100.0)
                .build();

        productDto = ProductDto.builder()
                .id(1)
                .quantity(2)
                .price(100.0)
                .build();
    }

    @Test
    void testAddProduct() {

       // when(productMapper.convertToEntity(productDto)).thenReturn(productEntity);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        productService.addProduct(productDto);


    }

    @Test
    void testAddProductFailure() {
    }
}

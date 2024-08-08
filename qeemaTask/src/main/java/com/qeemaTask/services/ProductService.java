package com.qeemaTask.services;


import com.qeemaTask.dtos.ProductDto;
import com.qeemaTask.entities.ProductEntity;
import com.qeemaTask.exceptions.ProductServiceException;
import com.qeemaTask.mappers.ProductMapper;
import com.qeemaTask.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    public void addProduct(ProductDto productDto) {
        try {
            logger.info("Start add Product ");
            ProductEntity productEntity = mapToEntity(productDto);
            validateProductEntity(productEntity);
            productRepository.save(productEntity);
        } catch (ProductServiceException ex) {
            logger.error("ProductServiceException occurred: {}", ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error("Error while try to add product", ex);
        }
    }

    public void validateProductEntity(ProductEntity productEntity) {
        if (productEntity.getPrice() <= 0) {
            throw new ProductServiceException("Price must be greater than 0");
        }
        if (productEntity.getQuantity() < 1) {
            throw new ProductServiceException("Quantity must be at least 1");
        }
    }

    public ProductEntity mapToEntity(ProductDto ProductDto) {
        return ProductMapper.INSTANCE.convertToEntity(ProductDto);
    }

    public ProductDto mapToDto(ProductEntity productEntity) {
        return ProductMapper.INSTANCE.convertToDto(productEntity);
    }
}

package com.qeemaTask.mappers;

import com.qeemaTask.dtos.ProductDto;
import com.qeemaTask.dtos.ProductDto.ProductDtoBuilder;
import com.qeemaTask.entities.ProductEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-08T11:29:31+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto convertToDto(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( productEntity.getId() );
        productDto.price( productEntity.getPrice() );
        productDto.quantity( productEntity.getQuantity() );
        productDto.name( productEntity.getName() );

        return productDto.build();
    }
}

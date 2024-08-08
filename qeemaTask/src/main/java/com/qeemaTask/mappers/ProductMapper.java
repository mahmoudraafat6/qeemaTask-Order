package com.qeemaTask.mappers;


import com.qeemaTask.dtos.ProductDto;
import com.qeemaTask.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto convertToDto(ProductEntity productEntity);

    default  ProductEntity convertToEntity(ProductDto productDto){
        return ProductEntity
                .builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
    }

}

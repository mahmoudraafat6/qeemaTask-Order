package com.qeemaTask.controllers;


import com.qeemaTask.dtos.ProductDto;
import com.qeemaTask.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService ProductService;
    @PostMapping("/addProduct")
    public String createProduct(@Valid @RequestBody ProductDto ProductDto)
    {
        ProductService.addProduct(ProductDto);
        return ProductDto.getName() + " added Successfully ";
    }

}

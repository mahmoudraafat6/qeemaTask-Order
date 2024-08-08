package com.qeemaTask.controllers;


import com.qeemaTask.dtos.OrderItemDto;
import com.qeemaTask.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;
    @GetMapping("/getAllOrderItems")
    public List<OrderItemDto> getAllOrderItems() {
        return orderItemService.getAllItems();
    }
}

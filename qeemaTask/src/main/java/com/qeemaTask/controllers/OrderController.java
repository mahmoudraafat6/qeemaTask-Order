package com.qeemaTask.controllers;

import com.qeemaTask.dtos.OrderDto;
import com.qeemaTask.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAllOrders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody OrderDto orderDto)
    {
        orderService.createOrder(orderDto);
        return "Order created Successfully";
    }
}

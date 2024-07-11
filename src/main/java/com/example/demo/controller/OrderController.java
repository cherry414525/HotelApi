package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.Order;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping
public class OrderController {
	
	 private final OrderService orderService;

	    public OrderController(OrderService orderService) {
	        this.orderService = orderService;
	    }

    @PostMapping("/api/orders")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        try {
        	orderService.processOrder(order);
            System.out.println("id="+order.getId()+",name="+order.getName()+",price="+order.getPrice()+",Currency="+order.getCurrency());
            return ResponseEntity.ok("Order processed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.example.springjpa.controller;

import com.example.springjpa.controller.dto.request.CreateOrderRequest;
import com.example.springjpa.controller.dto.request.UpdateOrderRequest;
import com.example.springjpa.controller.dto.response.OrdersResponse;
import com.example.springjpa.controller.dto.response.StatusResponse;
import com.example.springjpa.model.entity.OrderDetails;
import com.example.springjpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrdersResponse> getAllOrders() {
        List<OrdersResponse> response = this.orderService.getAllOrders();
        return response;
    }

    @GetMapping("/{id}")
    public OrdersResponse getOrderById(@PathVariable int id) {
        OrdersResponse ordersResponse = this.orderService.getOrderById(id);
        return ordersResponse;
    }

    @PostMapping
    public StatusResponse createOrder(@RequestBody CreateOrderRequest request) {
        String response  = this.orderService.createOrder(request);
        return new StatusResponse(response);
    }

    @PutMapping("/{id}")
    public StatusResponse updateOrder(@PathVariable int id, @RequestBody UpdateOrderRequest request) {
        String response = this.orderService.updateOrder(id, request);
        return new StatusResponse(response);
    }
/*
    @DeleteMapping("/{id}")
    public OrderDetails deletedOrder(@PathVariable int id) {
        OrderDetails deletedOrder = this.orderService.deleteOrder(id);
        return deletedOrder;
    }
 */
}

package com.example.springjpa.controller;

import com.example.springjpa.controller.dto.response.OrdersResponse;
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
/*
    @PostMapping
    public OrderDetails createOrder(@RequestBody OrderDetails order) {
        OrderDetails createdOrder = this.orderService.createOrder(order);
        return createdOrder;
    }

    @PutMapping("/{id}")
    public OrderDetails updateOrder(@PathVariable int id, @RequestBody OrderDetails order) {
        OrderDetails updatedOrder = this.orderService.updateOrder(id, order);
        return updatedOrder;
    }

    @DeleteMapping("/{id}")
    public OrderDetails deletedOrder(@PathVariable int id) {
        OrderDetails deletedOrder = this.orderService.deleteOrder(id);
        return deletedOrder;
    }
 */
}

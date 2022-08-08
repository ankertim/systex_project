package com.example.springrestfulpractice.controller;

import com.example.springrestfulpractice.model.Order;
import com.example.springrestfulpractice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        List <Order> orderList = this.orderService.getAllOrders();
        return orderList;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        Order order = this.orderService.getOrderById(id);
        return order;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Order createdOrder = this.orderService.createOrder(order);
        return createdOrder;
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) {
        Order updatedOrder = this.orderService.updateOrder(id, order);
        return updatedOrder;
    }

    @DeleteMapping("/{id}")
    public Order deletedOrder(@PathVariable int id) {
        Order deletedOrder = this.orderService.deleteOrder(id);
        return deletedOrder;
    }
}

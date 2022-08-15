package com.example.springjpa.service;

import com.example.springjpa.controller.dto.request.CreateOrderRequest;
import com.example.springjpa.controller.dto.request.UpdateOrderRequest;
import com.example.springjpa.controller.dto.response.OrdersResponse;
import com.example.springjpa.model.MealsRepository;
import com.example.springjpa.model.OrderDetailsRepository;
import com.example.springjpa.model.OrdersRepository;
import com.example.springjpa.model.entity.Meals;
import com.example.springjpa.model.entity.OrderDetails;
import com.example.springjpa.model.entity.Orders;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private MealsRepository mealsRepository;

    public List<OrdersResponse> getAllOrders() {
        List<OrdersResponse> ordersResponses = new ArrayList<>();
        List<Orders> orders = this.ordersRepository.findAll();
        // for each orders
        for (Orders order : orders) {
            int orderID = order.getOrderID();
            int totalPrice = order.getTotalPrice();
            String waiter = order.getWaiter();
            ordersResponses.add(new OrdersResponse(orderID, totalPrice, waiter));
            // Find all order in orderDetails
            List<OrderDetails> orderDetails = this.orderDetailsRepository.findByOrderID(orderID);
            for (OrderDetails orderDetail : orderDetails) {
                // Add all meals in orderDetails to orderResponse
                int mealID = orderDetail.getMealID();
                Meals meal = this.mealsRepository.findByMealID(mealID);
                // ArrayList is not best data structure, it can only use index to get
                ordersResponses.get(orderID - 1).getMealsList().add(meal);
            }
        }
        return ordersResponses;
    }

    public OrdersResponse getOrderById(int orderID) {
        // Find order by orderID
        Orders order = this.ordersRepository.findByOrderID(orderID);
        int totalPrice = order.getTotalPrice();
        String waiter = order.getWaiter();
        // Create orderResponse
        OrdersResponse ordersResponse = new OrdersResponse(orderID, totalPrice, waiter);
        // Find orderDetails by orderID
        List<OrderDetails> orderDetailsList = this.orderDetailsRepository.findByOrderID(orderID);
        for (OrderDetails orderDetail : orderDetailsList) {
            int mealID = orderDetail.getMealID();
            Meals meal = this.mealsRepository.findByMealID(mealID);
            ordersResponse.getMealsList().add(meal);
        }
        return ordersResponse;
    }

    public String createOrder(CreateOrderRequest request) {
        int orderID = request.getSeq();
        // Create order
        Orders order = new Orders(orderID, request.getTotalPrice(), request.getWaiter());
        this.ordersRepository.save(order);
        // Create orderDetail
        for (Meals meal : request.getMealsList()) {
            Long count = this.orderDetailsRepository.count();
            int orderDetailsID = (int)(count + 1);
            int mealID = meal.getMealID();
            OrderDetails orderDetail = new OrderDetails(orderDetailsID, orderID, mealID);
            this.orderDetailsRepository.save(orderDetail);
        }
        return "OK";
    }

    public String updateOrder(int orderID, UpdateOrderRequest request) {
        Orders order = this.ordersRepository.findByOrderID(orderID);
        // Update order
        order.setTotalPrice(request.getTotalPrice());
        order.setWaiter(request.getWaiter());
        this.ordersRepository.save(order);
        // Update orderDetail
        List<OrderDetails> orderDetailsList = this.orderDetailsRepository.findByOrderID(orderID);
        int idx = 0;
        for (Meals meal : request.getMealsList()) {
            // if orderDetailsList has data then update
            if (idx < orderDetailsList.size()) {
                int orderDetailsID = orderDetailsList.get(idx).getOrderDetailsID();
                OrderDetails orderDetail = new OrderDetails(orderDetailsID, orderID, meal.getMealID());
                this.orderDetailsRepository.save(orderDetail);
                idx++;
            } else {
                // if orderDetailsList's count of data less than updateRequest's, than insert
                Long count = this.orderDetailsRepository.count();
                int orderDetailsID = (int)(count + 1);
                OrderDetails orderDetail = new OrderDetails(orderDetailsID, orderID, meal.getMealID());
                this.orderDetailsRepository.save(orderDetail);
            }
        }

        // if orderDetailsList's count of data more than updateRequest's, than delete
        if (idx < orderDetailsList.size()) {
            int orderDetailsID = orderDetailsList.get(idx).getOrderDetailsID();
            Long count = this.orderDetailsRepository.deleteByOrderDetailsID(orderDetailsID);
        }
        return "OK";
    }
/*
    public OrderDetails deleteOrder(int id) {
        for (OrderDetails order : this.orderList) {
            if (id == order.getSeq()) {
                this.orderList.remove(order);
                return order;
            }
        }
        return null;
    }
     */
}

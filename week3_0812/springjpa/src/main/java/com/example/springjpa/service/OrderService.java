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
        List<OrdersResponse> ordersResponseList = new ArrayList<>();
        List<Orders> orders = this.ordersRepository.findAll();

        // for each orders
        for (Orders order : orders) {
            int orderID = order.getOrderID();
            int totalPrice = order.getTotalPrice();
            String waiter = order.getWaiter();
            // Add all orders in orderResponseList
            ordersResponseList.add(new OrdersResponse(orderID, totalPrice, waiter));
            // Find all order in orderDetails
            List<OrderDetails> orderDetails = this.orderDetailsRepository.findByOrderID(orderID);
            for (OrderDetails orderDetail : orderDetails) {
                // Add all meals in orderDetails to ordersResponse
                int mealID = orderDetail.getMealID();
                Meals meal = this.mealsRepository.findByMealID(mealID);
                // ArrayList is not best data structure, it can only use index to get
                for (OrdersResponse ordersResponse : ordersResponseList) {
                    if (orderID == ordersResponse.getSeq()) {
                        ordersResponse.getMealsList().add(meal);
                    }
                }
            }
        }
        return ordersResponseList;
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
        // find max of orderDetailsID
        List<OrderDetails> orderDetailsList = this.orderDetailsRepository.findAll();
        int max = 0;
        int insertID = 1;
        for (OrderDetails orderDetails : orderDetailsList) {
            if (max < orderDetails.getOrderDetailsID()) { max = orderDetails.getOrderDetailsID(); }
        }
        for (Meals meal : request.getMealsList()) {
            // How to place new data if data in orderDetails DB are disorder and OrderDetailsID is also random
            int orderDetailsID = ++max;
            int mealID = meal.getMealID();
            OrderDetails orderDetail = new OrderDetails(orderDetailsID, orderID, mealID);
            this.orderDetailsRepository.save(orderDetail);
        }
        return "OK";
    }

    public String updateOrder(int orderID, UpdateOrderRequest request) {
        Orders order = this.ordersRepository.findByOrderID(orderID);
        if (null == order) {
            return "FAIL";
        }

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
                // if orderDetailsList's count of data less than updateRequest's, then insert
                Long count = this.orderDetailsRepository.count();
                int orderDetailsID = (int)(count + 1);
                OrderDetails orderDetail = new OrderDetails(orderDetailsID, orderID, meal.getMealID());
                this.orderDetailsRepository.save(orderDetail);
            }
        }

        // if orderDetailsList's count of data more than updateRequest's, then delete
        if (idx < orderDetailsList.size()) {
            int orderDetailsID = orderDetailsList.get(idx).getOrderDetailsID();
            Long count = this.orderDetailsRepository.deleteByOrderDetailsID(orderDetailsID);
        }
        return "OK";
    }

    public String deleteOrderById(int orderID) {
        Orders order = this.ordersRepository.findByOrderID(orderID);
        if (null == order) {
            return "FAIL";
        }
        Long count1 = this.ordersRepository.deleteByOrderID(orderID);
        Long count2 = this.orderDetailsRepository.deleteByOrderID(orderID);
        return "OK";
    }
}

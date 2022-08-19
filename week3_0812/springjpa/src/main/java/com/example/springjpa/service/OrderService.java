package com.example.springjpa.service;

import com.example.springjpa.controller.dto.request.CreateOrderRequest;
import com.example.springjpa.controller.dto.request.UpdateOrderRequest;
import com.example.springjpa.controller.dto.response.OrderResponse;
import com.example.springjpa.model.MealRepository;
import com.example.springjpa.model.OrderDetailRepository;
import com.example.springjpa.model.OrderRepository;
import com.example.springjpa.model.entity.Meal;
import com.example.springjpa.model.entity.OrderDetail;
import com.example.springjpa.model.entity.Order;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private MealRepository mealRepository;

    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderResponseList = new ArrayList<>();
        List<Order> orderList = this.orderRepository.findAll();

        // for each order in orderList
        for (Order order : orderList) {
            int orderID = order.getOrderID();
            String waiter = order.getWaiter();
            // Add all orders in orderResponseList
            orderResponseList.add(new OrderResponse(orderID, waiter));
            // Find all orderDetail in orderDetailList
            List<OrderDetail> orderDetailList = this.orderDetailRepository.findByOrderID(orderID);
            for (OrderDetail orderDetail : orderDetailList) {
                // Add all meals in orderDetail to orderResponse
                int mealID = orderDetail.getMealID();
                Meal meal = this.mealRepository.findByMealID(mealID);
                // ArrayList is not best data structure, it can only use index to get
                for (OrderResponse orderResponse : orderResponseList) {
                    if (orderID == orderResponse.getSeq()) {
                        orderResponse.addMeal(meal);
                    }
                }
            }
        }
        return orderResponseList;
    }

    public OrderResponse getOrderById(int orderID) {

        // Find order by orderID
        Order order = this.orderRepository.findByOrderID(orderID);
        String waiter = order.getWaiter();

        // Create orderResponse
        OrderResponse orderResponse = new OrderResponse(orderID, waiter);
        // Find orderDetails by orderID
        List<OrderDetail> orderDetailList = this.orderDetailRepository.findByOrderID(orderID);
        for (OrderDetail orderDetail : orderDetailList) {
            int mealID = orderDetail.getMealID();
            Meal meal = this.mealRepository.findByMealID(mealID);
            orderResponse.addMeal(meal);
        }
        return orderResponse;
    }

    public String createOrder(CreateOrderRequest request) {
        int orderID = request.getSeq();

        // Create order
        Order order = new Order(orderID, request.getWaiter());
        this.orderRepository.save(order);

        // Create orderDetail
        // find max of orderDetailID
        List<OrderDetail> orderDetailList = this.orderDetailRepository.findAll();
        int max = 0;
        int insertID = 1;
        for (OrderDetail orderDetail : orderDetailList) {
            if (max < orderDetail.getOrderDetailID()) { max = orderDetail.getOrderDetailID(); }
        }
        for (Meal meal : request.getMealList()) {
            // How to place new data if data in orderDetail DB are disorder and OrderDetailID is also random
            int orderDetailID = ++max;
            int mealID = meal.getMealID();
            OrderDetail orderDetail = new OrderDetail(orderDetailID, orderID, mealID);
            this.orderDetailRepository.save(orderDetail);
        }
        return "OK";
    }

    public String updateOrder(int orderID, UpdateOrderRequest request) {
        Order order = this.orderRepository.findByOrderID(orderID);
        if (null == order) {
            return "FAIL";
        }

        // Update order
        order.setWaiter(request.getWaiter());
        this.orderRepository.save(order);

        // Update orderDetail
        List<OrderDetail> orderDetailList = this.orderDetailRepository.findByOrderID(orderID);
        int idx = 0;
        for (Meal meal : request.getMealList()) {
            // if orderDetailList has data then update
            if (idx < orderDetailList.size()) {
                int orderDetailID = orderDetailList.get(idx).getOrderDetailID();
                OrderDetail orderDetail = new OrderDetail(orderDetailID, orderID, meal.getMealID());
                this.orderDetailRepository.save(orderDetail);
                idx++;
            } else {
                // if orderDetailList's count of data less than updateRequest's, then insert
                Long count = this.orderDetailRepository.count();
                int orderDetailID = (int)(count + 1);
                OrderDetail orderDetail = new OrderDetail(orderDetailID, orderID, meal.getMealID());
                this.orderDetailRepository.save(orderDetail);
            }
        }

        // if orderDetailList's count of data more than updateRequest's, then delete
        if (idx < orderDetailList.size()) {
            int orderDetailID = orderDetailList.get(idx).getOrderDetailID();
            Long count = this.orderDetailRepository.deleteByOrderDetailID(orderDetailID);
        }
        return "OK";
    }

    public String deleteOrderById(int orderID) {
        Order order = this.orderRepository.findByOrderID(orderID);
        if (null == order) {
            return "FAIL";
        }
        Long count1 = this.orderRepository.deleteByOrderID(orderID);
        Long count2 = this.orderDetailRepository.deleteByOrderID(orderID);
        return "OK";
    }
}

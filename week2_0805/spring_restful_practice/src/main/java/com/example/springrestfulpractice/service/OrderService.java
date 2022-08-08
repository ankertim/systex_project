package com.example.springrestfulpractice.service;

import com.example.springrestfulpractice.model.Meal;
import com.example.springrestfulpractice.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    List<Order> orderList = new ArrayList<>();

    public OrderService() {
        List<Meal> mealInOrder1 = new ArrayList<>();
        List<Meal> mealInOrder2 = new ArrayList<>();
        //order1
        Meal meal1 = new Meal("hamburger", 100, "This is delicious.");
        Meal meal2 = new Meal("sandwich", 50, "This is sandwich.");
        Meal meal3 = new Meal("juice", 20, "This is juice.");
        mealInOrder1.add(meal1);
        mealInOrder1.add(meal2);
        mealInOrder1.add(meal3);
        orderList.add(new Order(1, 170, "bill", mealInOrder1));
        //order2
        Meal meal4 = new Meal("rice", 10, "This is rice.");
        Meal meal5 = new Meal("egg", 20, "This is egg.");
        mealInOrder2.add(meal4);
        mealInOrder2.add(meal5);
        this.orderList.add(new Order(2, 30, "Ann", mealInOrder2));
    }

    public List<Order> getAllOrders() {
        return this.orderList;
    }

    public Order getOrderById(int id) {
        for (Order order : this.orderList) {
            if (id == order.getSeq()) {
                return order;
            }
        }
        return null;
    }

    public Order createOrder(Order order) {
        this.orderList.add(order);
        return order;
    }

    public Order updateOrder(int id, Order order) {
        for (Order updatedOrder : this.orderList) {
            if (id == updatedOrder.getSeq()) {
                updatedOrder.setTotalPrice(order.getTotalPrice());
                updatedOrder.setWaiter(order.getWaiter());
                updatedOrder.setMealList(order.getMealList());
                return updatedOrder;
            }
        }
        return null;
    }

    public Order deleteOrder(int id) {
        for (Order order : this.orderList) {
            if (id == order.getSeq()) {
                this.orderList.remove(order);
                return order;
            }
        }
        return null;
    }
}

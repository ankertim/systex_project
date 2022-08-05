package com.practice.demo.service;

import com.practice.demo.model.Meal;
import com.practice.demo.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    List<Order> orderList = new ArrayList<>();

    public OrderService(List<Meal> mealList) {
        List<Meal> mealInOrder1 = new ArrayList<>();
        List<Meal> mealInOrder2 = new ArrayList<>();
        //order1
        Meal meal1 = new Meal("hamburger", 100, "This is delicious.");
        Meal meal2 = new Meal("sandwich", 50, "This is sandwich.");
        Meal meal3 = new Meal("juice", 20, "This is juice.");
        mealInOrder1.add(meal1);
        mealInOrder1.add(meal2);
        mealInOrder1.add(meal3);
        orderList.add(new Order(1, "bill", mealInOrder1));
        //order2
        Meal meal4 = new Meal("rice", 10, "This is rice.");
        Meal meal5 = new Meal("egg", 20, "This is egg.");
        mealInOrder2.add(meal4);
        mealInOrder2.add(meal5);
        this.orderList.add(new Order(2, "Ann", mealInOrder2));
    }

    public Order getOrderBySeq(String s) {
        int seq = Integer.parseInt(s);
        for (int i=0; i<orderList.size(); i++) {
            if (this.orderList.get(i).getSeq() == seq) {
                return this.orderList.get(i);
            }
        }
        return null;
    }

    public int getIncome() {
        int income = 0;
        for (int i=0; i<orderList.size(); i++) {
            income += this.orderList.get(i).getTotalPrice();
        }
        return income;
    }
}

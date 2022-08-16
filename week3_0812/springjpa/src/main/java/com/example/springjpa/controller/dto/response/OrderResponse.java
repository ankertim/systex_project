package com.example.springjpa.controller.dto.response;

import com.example.springjpa.model.entity.Meal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int seq;
    private int totalPrice;
    private String waiter;
    private List<Meal> mealList;

    public OrderResponse(int seq, String waiter) {
        this.seq = seq;
        this.waiter = waiter;
        this.totalPrice = 0;
        this.mealList = new ArrayList<>();
    }

    public void setTotalPrice() {
        int totalPrice = 0;
        for (Meal meal : this.mealList) {
            totalPrice += meal.getPrice();
        }
        this.totalPrice = totalPrice;
    }

    public void addMeal(Meal meal) {
        this.mealList.add(meal);
        this.setTotalPrice();
    }


}

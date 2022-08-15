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

    public OrderResponse(int seq, int totalPrice, String waiter) {
        this.seq = seq;
        this.totalPrice = totalPrice;
        this.waiter = waiter;
        this.mealList = new ArrayList<>();
    }
}

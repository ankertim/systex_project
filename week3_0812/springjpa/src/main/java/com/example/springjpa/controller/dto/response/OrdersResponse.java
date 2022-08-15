package com.example.springjpa.controller.dto.response;

import com.example.springjpa.model.entity.Meals;
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
public class OrdersResponse {
    private int seq;
    private int totalPrice;
    private String waiter;
    private List<Meals> mealsList;

    public OrdersResponse(int seq, int totalPrice, String waiter) {
        this.seq = seq;
        this.totalPrice = totalPrice;
        this.waiter = waiter;
        this.mealsList = new ArrayList<>();
    }
}

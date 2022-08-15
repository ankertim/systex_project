package com.example.springjpa.controller.dto.request;

import com.example.springjpa.model.entity.Meals;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private int seq;
    private int totalPrice;
    private String waiter;
    private List<Meals> mealsList;
}

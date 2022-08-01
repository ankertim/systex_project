package com.practice.demo.service;

import com.practice.demo.model.Meal;
import com.practice.demo.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {
    List<Meal> mealList = new ArrayList<>();

    public MealService(List<Meal> mealList) {
        this.mealList.add(new Meal("hamburger", 100, "This is delicious."));
    }
    public Meal getMealByName(String name) {
       for (int i=0; i<this.mealList.size(); i++) {
           if (this.mealList.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
               return this.mealList.get(i);
           }
       }
       return null;
    }

    //List<Meal> mealList = new ArrayList<>();


}

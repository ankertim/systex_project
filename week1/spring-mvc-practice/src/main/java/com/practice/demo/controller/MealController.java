package com.practice.demo.controller;

import com.practice.demo.model.Meal;
import com.practice.demo.model.Order;
import com.practice.demo.service.MealService;
import com.practice.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/meal")
public class MealController {
    @Autowired
    MealService mealService;

    @GetMapping()
    public String getMealByName(@RequestParam String name, Model model) {
        Meal meal = this.mealService.getMealByName(name);
        model.addAttribute("meal", meal);
        return "meal";
    }

}

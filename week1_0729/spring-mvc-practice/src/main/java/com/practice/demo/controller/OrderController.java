package com.practice.demo.controller;

import com.practice.demo.model.Order;
import com.practice.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{seq}")
    public String getOrderSeq(@PathVariable String seq, Model model) {
        Order order = this.orderService.getOrderBySeq(seq);
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/income")
    public String getIncome(Model model) {
        int income = this.orderService.getIncome();
        model.addAttribute("income", income);
        return "income";
    }
}

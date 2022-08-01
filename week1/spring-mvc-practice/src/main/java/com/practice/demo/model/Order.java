package com.practice.demo.model;
import java.util.List;

public class Order {
    private int seq;
    private int totalPrice;
    private String waiter;
    private List<Meal> meals;

    public Order(int seq, String waiter, List<Meal> meals) {
        this.seq = seq;
        this.waiter = waiter;
        this.meals = meals;
        this.totalPrice = 0;
        for (int i=0; i<meals.size(); i++) {
            this.totalPrice += meals.get(i).getPrice();
        }
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}

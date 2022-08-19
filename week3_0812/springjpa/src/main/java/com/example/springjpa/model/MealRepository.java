package com.example.springjpa.model;

import com.example.springjpa.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {
    Meal findByMealID(int mealID);
}

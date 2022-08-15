package com.example.springjpa.model;

import com.example.springjpa.model.entity.Meals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealsRepository extends JpaRepository<Meals, Integer> {
    Meals findByMealID(int mealID);
}

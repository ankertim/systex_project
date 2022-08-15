package com.example.springjpa.model;

import com.example.springjpa.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByOrderID(int orderID);

    @Transactional
    Long deleteByOrderID(int orderID);
}
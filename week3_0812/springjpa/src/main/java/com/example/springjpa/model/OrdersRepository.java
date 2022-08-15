package com.example.springjpa.model;

import com.example.springjpa.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Orders findByOrderID(int orderID);

    @Transactional
    Long deleteByOrderID(int orderID);
}
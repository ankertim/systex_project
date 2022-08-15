package com.example.springjpa.model;

import com.example.springjpa.model.entity.OrderDetails;
import com.example.springjpa.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByOrderID(int orderID);
}

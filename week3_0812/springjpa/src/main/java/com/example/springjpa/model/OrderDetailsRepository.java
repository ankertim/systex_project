package com.example.springjpa.model;

import com.example.springjpa.model.entity.OrderDetails;
import com.example.springjpa.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByOrderID(int orderID);
    OrderDetails findByOrderDetailsID(int orderDetailsID);
    Long deleteByOrderDetailsID(int orderDetailsID);

    @Transactional
    Long deleteByOrderID(int orderID);
}

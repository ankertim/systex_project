package com.example.springjpa.model;

import com.example.springjpa.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderID(int orderID);
    OrderDetail findByOrderDetailID(int orderDetailID);
    Long deleteByOrderDetailID(int orderDetailID);

    @Transactional
    Long deleteByOrderID(int orderID);
}

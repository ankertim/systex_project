package com.example.springjpa.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details") // if table's name = orderDetails, h2 database will create order_details.
public class OrderDetails {
    @Id
    @Column
    private int orderDetailsID; //if column's name is orderDetailsID, h2 database will create order_datailsID.

    @Column
    private int orderID;

    @Column
    private int mealID;
}

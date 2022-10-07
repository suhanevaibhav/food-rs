package com.suhane.food.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "online_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "quantity")
    private Float itemQuantity;
    @Column(name = "vendor")
    private String vendor;
    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "order_address")
    private String orderAddress;
    @Column(name = "order_name")
    private String orderName;
}

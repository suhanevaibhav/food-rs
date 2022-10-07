package com.suhane.food.dto;

import lombok.Data;

@Data
public class OrderDetails {
    private Integer orderId;
    private String itemCode;
    private String itemName;
    private Float itemQuantity;
    private String vendor;
    private String paymentStatus;
    private Integer money;
    private String orderAddress;
    private String orderName;
}

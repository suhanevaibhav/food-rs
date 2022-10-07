package com.suhane.food.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class Order {

    private String itemCode;
    private String itemName;
    private Float itemQuantity;
    private String vendor;
    private String accNum;
    private Integer money;
    private String orderAddress;
    private String orderName;

}

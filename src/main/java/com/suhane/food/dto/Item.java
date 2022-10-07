package com.suhane.food.dto;

import lombok.Data;

import java.util.Date;
@Data
public class Item {

    private String itemCode;
    private String itemName;
    private Float itemPrice;
    private String itemMfd;
    private String itemExpiryDate;
    private Float itemQuantity;
    private String vendor;

}

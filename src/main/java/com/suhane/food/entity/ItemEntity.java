package com.suhane.food.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@ToString
@Table(name = "item")
@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "name")
    private String itemName;
    @Column(name = "price")
    private Float itemPrice;
    @Column(name = "mfd")
    private Date itemMfd;
    @Column(name = "exp_date")
    private Date itemExpiryDate;
    @Column(name = "quantity")
    private Float itemQuantity;
    @Column(name = "vendor")
    private String vendor;
}

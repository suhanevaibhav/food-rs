package com.suhane.food.dto;

import lombok.Data;

@Data
public class Account {

    private String accountNumber;
    private String accountHolderName;
    private String accountLoginName;
    private String accountPwd;
    private Integer amount;
}


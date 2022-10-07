package com.suhane.food.utility;

import com.suhane.food.dto.Item;
import com.suhane.food.entity.ItemEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FoodUtilty {

    SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    public Item covertEntityToDto(ItemEntity entity) {
        Item item = new Item();
        item.setItemCode(entity.getItemCode());
        item.setItemName(entity.getItemName());
        item.setItemPrice(entity.getItemPrice());
        item.setItemMfd(convertDateToString(entity.getItemMfd()));
        item.setItemQuantity(entity.getItemQuantity());
        item.setItemExpiryDate(convertDateToString(entity.getItemExpiryDate()));
        item.setVendor(entity.getVendor());
        return item;
    }

    public ItemEntity convertDtoToEntity(Item item) {
        ItemEntity entity = new ItemEntity();
        entity.setItemCode(item.getItemCode());
        entity.setItemName(item.getItemName());
        entity.setItemPrice(item.getItemPrice());
        entity.setItemQuantity(item.getItemQuantity());
        entity.setItemMfd(convertStringToDate(item.getItemMfd()));
        entity.setItemExpiryDate(convertStringToDate(item.getItemExpiryDate()));
        entity.setVendor(item.getVendor());

        return entity;
    }

    public Date convertStringToDate(String strDate) {

        Date date = new Date();
        try {
            date = dateformat.parse(strDate);
        } catch (Exception e) {
            System.out.println(e);
        }
        return date;
    }
    public String convertDateToString(Date date) {

       return dateformat.format(date);
    }
}

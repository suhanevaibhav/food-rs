package com.suhane.food.controller;

import com.suhane.food.dto.Item;
import com.suhane.food.exception.FoodServiceException;
import com.suhane.food.service.ItemServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/food/item")
public class ItemController {

    private static final Logger logger = LogManager.getLogger(ItemController.class);
    @Autowired
    private ItemServiceImpl itemService;

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveItem(@RequestBody Item item) {
        try {
            Item resItem = itemService.saveItem(item);
            logger.info("save the item successfully : {}",item);
            return new ResponseEntity<>(resItem, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Failed the item to save : {}",item);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }


    }
   @GetMapping(value = "/items")
    public ResponseEntity<Object> findAllItem() {
        try {
            List<Item> itemList = itemService.FindAllItem();
            logger.info("get item details : {}", itemList);
            return new ResponseEntity<>(itemList, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Failed to get item details");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

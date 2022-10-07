package com.suhane.food.controller;

import com.suhane.food.dto.Order;
import com.suhane.food.dto.OrderDetails;
import com.suhane.food.exception.FoodServiceException;
import com.suhane.food.service.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food/order")
public class OrderController {

    private static final Logger log = LogManager.getLogger(OrderController.class);
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping(value = "/book/order")
    public ResponseEntity<Object> bookOrder(@RequestBody Order order){

        try {
           OrderDetails orderDetails = orderService.bookOrder(order);
           log.info("successfully book the order");
           return new ResponseEntity<>(orderDetails, HttpStatus.OK);
        } catch (Exception e) {
            log.info("failed the order booking ");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

}

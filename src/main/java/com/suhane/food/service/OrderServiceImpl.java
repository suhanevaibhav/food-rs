package com.suhane.food.service;

import com.suhane.food.dto.Account;
import com.suhane.food.dto.Item;
import com.suhane.food.dto.Order;
import com.suhane.food.dto.OrderDetails;
import com.suhane.food.entity.ItemEntity;
import com.suhane.food.entity.OrderEntity;
import com.suhane.food.exception.FoodServiceException;
import com.suhane.food.integration.PaymentIntegration;
import com.suhane.food.repository.ItemRepository;
import com.suhane.food.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
public class OrderServiceImpl {

    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentIntegration integration;

    public OrderDetails bookOrder(Order order) {
    ItemEntity itemEntity = itemRepository.findOrderByItemNameAndCodeAndVendor(order.getItemCode()
               , order.getItemName(), order.getVendor());

    if (itemEntity == null || ObjectUtils.isEmpty(itemEntity)) {
        log.info("order details are invalid ");
        throw new FoodServiceException("order details are invalid ");
    }

    if (itemEntity.getItemQuantity() - order.getItemQuantity() < 0) {
        log.info("order quantity {} is not available currently {} kg is available ",order.getItemQuantity()
                ,itemEntity.getItemQuantity());
        throw new FoodServiceException("order quantity is not available currently available is : "
                +itemEntity.getItemQuantity() );
    }

    Integer balance = integration.getAmountDetails(order.getAccNum());
    if (balance - order.getMoney() <0) {
        log.info("insufficient balance: {}",balance);
        throw new FoodServiceException("insufficient balance: %d " +balance);
    }

    Account paymentDetilas = integration.getPaymentStatus(order.getAccNum(), order.getMoney());

    if (paymentDetilas == null || ObjectUtils.isEmpty(paymentDetilas)) {
        log.info("payment failed");
        throw new FoodServiceException("payment failed");
    }

    Float updateQuantity = itemEntity.getItemQuantity() - order.getItemQuantity();
    itemEntity.setItemQuantity(updateQuantity);
    itemRepository.save(itemEntity);
    log.info("Item save successfully ");
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setOrderAddress(order.getOrderAddress());
    orderEntity.setOrderName(order.getOrderName());
    orderEntity.setItemQuantity(order.getItemQuantity());
    orderEntity.setVendor(order.getVendor());
    orderEntity.setPaymentStatus("Successful");
    orderEntity.setAmount(order.getMoney());
    orderEntity.setItemName(order.getItemName());
    orderEntity.setItemCode(order.getItemCode());

    OrderEntity entity = orderRepository.save(orderEntity);
    log.info("Order save Successfully ");


     OrderDetails orderDetails = new OrderDetails();
     orderDetails.setOrderId(entity.getOrderId());
     orderDetails.setOrderAddress(entity.getOrderAddress());
     orderDetails.setOrderName(entity.getOrderName());
     orderDetails.setItemName(entity.getItemName());
     orderDetails.setItemQuantity(entity.getItemQuantity());
     orderDetails.setMoney(entity.getAmount());
     orderDetails.setPaymentStatus(entity.getPaymentStatus());
     orderDetails.setVendor(entity.getVendor());
     orderDetails.setItemCode(entity.getItemCode());
     log.info("Order Details : {}",orderDetails);
     return orderDetails;


    }
}

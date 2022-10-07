package com.suhane.food.exception;

public class FoodServiceException extends RuntimeException {

    private String message;

    public FoodServiceException() {
    }
     public FoodServiceException(String message) {
         super(message);
         this.message = message;
     }
}

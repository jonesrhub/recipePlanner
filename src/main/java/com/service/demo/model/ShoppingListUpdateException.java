package com.service.demo.model;

public class ShoppingListUpdateException extends UpdateException {

    // Parameterless Constructor
    public ShoppingListUpdateException() {}

    // Constructor that accepts a message
    public ShoppingListUpdateException(String message)
    {
        super(message);
    }

}

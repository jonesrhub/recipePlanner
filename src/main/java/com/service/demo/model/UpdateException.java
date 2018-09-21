package com.service.demo.model;

public class UpdateException extends Exception{
    // Parameterless Constructor
    public UpdateException() {}

    // Constructor that accepts a message
    public UpdateException(String message)
    {
        super(message);
    }
}

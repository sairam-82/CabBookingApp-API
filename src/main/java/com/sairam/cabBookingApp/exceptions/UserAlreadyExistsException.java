package com.sairam.cabBookingApp.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){

    }
    public UserAlreadyExistsException(String message){
        super(message);
    }
}

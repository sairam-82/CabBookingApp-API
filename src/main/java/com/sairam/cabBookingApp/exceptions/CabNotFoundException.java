package com.sairam.cabBookingApp.exceptions;

public class CabNotFoundException extends RuntimeException{
    public CabNotFoundException(){

    }
    public CabNotFoundException(String message){
        super(message);
    }
}

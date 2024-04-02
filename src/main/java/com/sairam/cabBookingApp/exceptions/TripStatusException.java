package com.sairam.cabBookingApp.exceptions;

public class TripStatusException extends RuntimeException{
    public TripStatusException(){

    }
    public TripStatusException(String message){
        super(message);
    }
}

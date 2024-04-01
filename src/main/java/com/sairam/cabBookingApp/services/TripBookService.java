package com.sairam.cabBookingApp.services;

import com.sairam.cabBookingApp.controllers.exchanges.requests.TripBookRegisterRequest;

public interface TripBookService {

    String addTrip(TripBookRegisterRequest tripBookRegisterRequest);

}


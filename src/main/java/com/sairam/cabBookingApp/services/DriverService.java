package com.sairam.cabBookingApp.services;

import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.DriverDetailsResponse;
import com.sairam.cabBookingApp.models.Cab;
import com.sairam.cabBookingApp.models.Driver;

public interface DriverService {
    AuthenticationResponse register(Driver driver);
    String addCab(Cab cab);
    DriverDetailsResponse getDriverDetails(Long id);
}

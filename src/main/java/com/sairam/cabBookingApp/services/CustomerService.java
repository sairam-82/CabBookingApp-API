package com.sairam.cabBookingApp.services;

import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.models.Customer;

public interface CustomerService {
    public AuthenticationResponse register(Customer customer);
}

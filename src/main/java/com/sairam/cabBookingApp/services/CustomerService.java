package com.sairam.cabBookingApp.services;

import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.CustomerDetailsResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.DriverDetailsResponse;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.TripBook;

import java.util.List;

public interface CustomerService {
    public AuthenticationResponse register(Customer customer);
    CustomerDetailsResponse getCustomerDetails(Long id);

    AuthenticationResponse updateCustomerDetails(Customer customer);

    List<TripBook> getAllTripBooks();

    String deleteCustomer(Long id);

}

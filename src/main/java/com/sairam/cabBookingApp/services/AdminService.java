package com.sairam.cabBookingApp.services;

import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.GetTripBooksResponse;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Driver;
import com.sairam.cabBookingApp.models.User;

import java.util.List;

public interface AdminService {

    public AuthenticationResponse register(User user);
    AuthenticationResponse updateAdminDetails(User admin);
    List<Customer> getAllCustomers();
    List<Driver> getAllDrivers();

    GetTripBooksResponse getCustomerTrips(Long userId);
    List<Driver> getTopDrivers();
    Driver getDriverDetails(Long id);
    Customer getCustomerDetails(Long customerId);

    String deleteUser(Long id);
}

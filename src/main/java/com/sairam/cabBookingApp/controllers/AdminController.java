package com.sairam.cabBookingApp.controllers;


import com.sairam.cabBookingApp.controllers.exchanges.requests.TripBookRegisterRequest;
import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.GetTripBooksResponse;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Driver;
import com.sairam.cabBookingApp.models.User;
import com.sairam.cabBookingApp.services.AdminService;
import com.sairam.cabBookingApp.services.CustomerService;
import com.sairam.cabBookingApp.services.DriverService;
import com.sairam.cabBookingApp.services.TripBookService;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private TripBookService tripBookService;

    @Autowired
    private DriverService driverService;

    @PutMapping
    public ResponseEntity<AuthenticationResponse> updateAdmin(@Valid @RequestBody User admin){
        return ResponseEntity.ok().body(adminService.updateAdminDetails(admin));
    }

    @GetMapping("/trip-bookings")
    public ResponseEntity<GetTripBooksResponse> allTripBooks(){
        return ResponseEntity.ok().body(tripBookService.getAllTrips());
    }

    @GetMapping("/trip-bookings/date-wise/{date}")
    public ResponseEntity<GetTripBooksResponse> getTripsDateWise(@PathVariable LocalDate date){

        return ResponseEntity.ok().body(tripBookService.getTripsOnDate(date));
    }
    @GetMapping("/customers")
    public  ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> allCustomers= new ArrayList<>();
        allCustomers=adminService.getAllCustomers();
        return ResponseEntity.ok().body(allCustomers);
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers(){
        List<Driver> drivers = new ArrayList<>();
        drivers=adminService.getAllDrivers();
       return ResponseEntity.ok().body(drivers);
    }

    @GetMapping("/{userId}/trip-bookings")
    public ResponseEntity<GetTripBooksResponse> getCustomerTrips(@PathVariable Long userId){
        return ResponseEntity.ok().body(adminService.getCustomerTrips(userId));

    }

    @GetMapping("/top-drivers")
    public ResponseEntity<List<Driver>> getTopDrivers(){
        return ResponseEntity.ok().body(adminService.getTopDrivers());
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<Driver> getDriverDetails(@PathVariable Long driverId){
        return ResponseEntity.ok().body(adminService.getDriverDetails(driverId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerDetails(@PathVariable Long customerId){
        return ResponseEntity.ok().body(adminService.getCustomerDetails(customerId));
    }

    @PutMapping("/trip-bookings/{tripId}")
    public ResponseEntity<String> updateTripBooking(@PathVariable Long tripId, @RequestBody TripBookRegisterRequest tripBookRegisterRequest){
        return ResponseEntity.ok().body(tripBookService.addTrip(tripBookRegisterRequest));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        return ResponseEntity.ok().body(adminService.deleteUser(userId));
    }

    @DeleteMapping("/trip-booking/{tripId}")
    public ResponseEntity<String> deleteTripBook(@PathVariable Long tripId){
        return ResponseEntity.ok().body(tripBookService.deleteTrip(tripId));
    }











}

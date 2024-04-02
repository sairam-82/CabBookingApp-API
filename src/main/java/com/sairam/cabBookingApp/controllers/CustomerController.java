package com.sairam.cabBookingApp.controllers;

import com.sairam.cabBookingApp.controllers.exchanges.requests.RateDriverRequest;
import com.sairam.cabBookingApp.controllers.exchanges.requests.TripBookRegisterRequest;
import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.CustomerDetailsResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.GetTripBooksResponse;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.repositories.TripBookRepository;
import com.sairam.cabBookingApp.services.CustomerService;
import com.sairam.cabBookingApp.services.TripBookService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private TripBookService tripBookService;
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDetailsResponse> getCustomerDetails(@PathVariable Long customerId){
        return ResponseEntity.ok().body(customerService.getCustomerDetails(customerId));
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<AuthenticationResponse> updateCustomer(@Valid @RequestBody Customer customer){
        return ResponseEntity.ok().body(customerService.updateCustomerDetails(customer));
    }

    @PostMapping("/customer/add-trip-book")
    public ResponseEntity<String> addTripBook(@RequestBody TripBookRegisterRequest tripBookRegisterRequest){
        return ResponseEntity.ok().body(tripBookService.addTrip(tripBookRegisterRequest));
    }

    @PutMapping("/customer/rate-driver")
    public ResponseEntity<String> rateDriver(@RequestBody RateDriverRequest rateDriverRequest){
        return ResponseEntity.ok().body(tripBookService.rateDriver(rateDriverRequest));
    }

    @GetMapping("/customer/trip-bookings")
    public ResponseEntity<GetTripBooksResponse> getTripBooks(){
        return ResponseEntity.ok().body(
                new GetTripBooksResponse(customerService.getAllTripBooks()));
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long customerId){
        return ResponseEntity.ok().body(customerService.deleteCustomer(customerId));
    }








}

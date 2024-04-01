package com.sairam.cabBookingApp.controllers;

import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Driver;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.repositories.DriverRepository;
import com.sairam.cabBookingApp.services.CustomerService;
import com.sairam.cabBookingApp.services.DriverService;
import com.sairam.cabBookingApp.services.implementations.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthenticationController {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private DriverService driverService;

    @PostMapping("/auth/customer/register")
    public ResponseEntity<AuthenticationResponse> postCustomerRegister(@Valid @RequestBody Customer customer){

        return ResponseEntity.ok().body(customerService.register(customer));
    }

    @PostMapping("/auth/driver/register")
    public ResponseEntity<AuthenticationResponse> getDriverRegister(@Valid @RequestBody Driver driver){

        return ResponseEntity.ok().body(driverService.register(driver));
    }

    @GetMapping("/check")
    public String check(){
        var x= SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(x);
        return "auth";
    }



}

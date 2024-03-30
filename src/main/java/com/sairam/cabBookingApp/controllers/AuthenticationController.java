package com.sairam.cabBookingApp.controllers;

import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.services.CustomerService;
import com.sairam.cabBookingApp.services.implementations.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/register")
    public ResponseEntity<Customer> getRegister(@Valid @RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return ResponseEntity.ok().body(customerService.register(customer));
    }

    @GetMapping("/check")
    public String check(){
        return "authenticated";
    }
    @GetMapping("/simple")
    public String simple(){
        return customerRepository.findCustomerByFirstName("JohnCena").getPassword()+" "+customerRepository.findCustomerByFirstName("John").getUsername();
    }
}

package com.sairam.cabBookingApp.controllers;

import com.sairam.cabBookingApp.controllers.exchanges.requests.AuthenticationRequest;
import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Driver;
import com.sairam.cabBookingApp.models.User;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.repositories.DriverRepository;
import com.sairam.cabBookingApp.services.AdminService;
import com.sairam.cabBookingApp.services.AuthenticationService;
import com.sairam.cabBookingApp.services.CustomerService;
import com.sairam.cabBookingApp.services.DriverService;
import com.sairam.cabBookingApp.services.implementations.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//@RequestMapping("/auth")
@RestController
public class AuthenticationController {


    @Autowired
    private CustomerService customerService;
    @Autowired
    private DriverService driverService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationService authenticationService;



    @PostMapping("/auth/customer/register")
    public ResponseEntity<AuthenticationResponse> postCustomerRegister(@RequestBody Customer customer){

        return ResponseEntity.ok().body(customerService.register(customer));
    }

    @PostMapping("/auth/driver/register")
    public ResponseEntity<AuthenticationResponse> getDriverRegister(@Valid @RequestBody Driver driver){

        return ResponseEntity.ok().body(driverService.register(driver));
    }

    @PostMapping("/auth/admin/register")
    public ResponseEntity<AuthenticationResponse> getAdminRegister(@Valid @RequestBody User admin){

        return ResponseEntity.ok().body(adminService.register(admin));
    }

    @PostMapping("/auth/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        authenticationService.refreshToken(request, response);
    }

    @PostMapping("auth/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/check")
    public String check(){
        var x= SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(x);
        return "auth";
    }



}

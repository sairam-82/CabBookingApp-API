package com.sairam.cabBookingApp.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.DriverDetailsResponse;
import com.sairam.cabBookingApp.exceptions.DriverNotFoundException;
import com.sairam.cabBookingApp.models.Cab;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Driver;
import com.sairam.cabBookingApp.models.Token;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.repositories.DriverRepository;
import com.sairam.cabBookingApp.services.DriverService;
import com.sairam.cabBookingApp.services.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

   @Autowired
   private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse register(Driver driver){
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
//        customerRepository.save(customer);
//        Customer c=customerRepository.findCustomerByEmail(customer.getEmail());
        String jwtToken=jwtService.generateToken(driver);
        String refreshToken=jwtService.generateRefreshToken(driver);
        driver.setToken(Token.builder().tokenString(jwtToken).expired(false).revoked(false).build());
        driverRepository.save(driver);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public String addCab(Cab cab) {
        String userEmail=SecurityContextHolder.getContext().getAuthentication().getName();
        Driver driver=driverRepository.findDriverByEmail(userEmail).orElseThrow(
                ()->new DriverNotFoundException("Driver Not Found with this mail: "+userEmail)
        );
        driver.setCab(cab);
        driverRepository.save(driver);
        return "success";
    }

    @Override
    public DriverDetailsResponse getDriverDetails(Long id) {
        Driver driver=driverRepository.findDriverById(id).orElseThrow(
                ()-> new DriverNotFoundException("Driver Not Found with this id: "+id)
        );
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(driver,DriverDetailsResponse.class);
    }

}

package com.sairam.cabBookingApp.services.implementations;

import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Token;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.services.CustomerService;
import com.sairam.cabBookingApp.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse register(Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
//        customerRepository.save(customer);
//        Customer c=customerRepository.findCustomerByEmail(customer.getEmail());
        String jwtToken=jwtService.generateToken(customer);
        String refreshToken=jwtService.generateRefreshToken(customer);
        customer.setToken(Token.builder().tokenString(jwtToken).expired(false).revoked(false).build());
        customerRepository.save(customer);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

}

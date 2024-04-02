package com.sairam.cabBookingApp.services.implementations;

import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.GetTripBooksResponse;
import com.sairam.cabBookingApp.exceptions.CustomerNotFoundException;
import com.sairam.cabBookingApp.exceptions.DriverNotFoundException;
import com.sairam.cabBookingApp.exceptions.UserAlreadyExistsException;
import com.sairam.cabBookingApp.exceptions.UserNotFoundException;
import com.sairam.cabBookingApp.models.*;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.repositories.DriverRepository;
import com.sairam.cabBookingApp.repositories.UserRepository;
import com.sairam.cabBookingApp.services.AdminService;
import com.sairam.cabBookingApp.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse register(User user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "Admin already exists with this email " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String jwtToken=jwtService.generateToken(user);
        String refreshToken=jwtService.generateRefreshToken(user);
        user.setToken(Token.builder().tokenString(jwtToken).expired(false).revoked(false).build());
        userRepository.save(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();    }

    @Override
    public AuthenticationResponse updateAdminDetails(User newCustomer) {
        User customer=userRepository.findUserByEmail(newCustomer.getEmail()).
                orElseThrow(()-> new CustomerNotFoundException("Customer Not found with id: "+newCustomer.getUserId()));
        customer.setEmail(newCustomer.getEmail());
        customer.setAddress(newCustomer.getAddress());
        customer.setRole(newCustomer.getRole());
        customer.setMobileNumber(newCustomer.getMobileNumber());
        customer.setLastName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        String jwtToken=jwtService.generateToken(customer);

        String refreshToken=jwtService.generateRefreshToken(customer);
        customer.setToken(Token.builder().tokenString(jwtToken).expired(false).revoked(false).build());
        userRepository.save(customer);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public GetTripBooksResponse getCustomerTrips(Long userId) {
        User user=userRepository.findUserById(userId).
                orElseThrow(()-> new UserNotFoundException("User Not Found with id: "+userId));
        List<TripBook> trips=new ArrayList<>();
        if (user.getRole().equals("driver")){
            trips=driverRepository.findDriverById(userId).get().getDriverTrips();
        }
        else if(user.getRole().equals("customer")){
           trips=customerRepository
                    .findCustomerById(userId).get().getTrips();
        }
        return new GetTripBooksResponse(trips);
    }

    @Override
    public List<Driver> getTopDrivers() {
        return driverRepository.findTopDrivers();
    }

    @Override
    public Driver getDriverDetails(Long id) {
        return driverRepository.findDriverById(id).orElseThrow(
                ()-> new DriverNotFoundException("Driver Not Found with this id: "+id)
        );
    }

    @Override
    public Customer getCustomerDetails(Long id) {
        return customerRepository.findCustomerById(id).orElseThrow(
                () -> new DriverNotFoundException("Driver Not Found with this id: " + id)
        );
    }

    @Override
    public String deleteUser(Long id) {
        User user=userRepository.findUserById(id).orElseThrow(
                ()->new UserNotFoundException("User Not Found to delete")
        );

        if (user.getRole().equals("driver")){
            driverRepository.deleteById(id);
        }
        else if(user.getRole().equals("customer")){
            customerRepository.deleteById(id);
        }
        return "Deleted the user successfully";
    }
}

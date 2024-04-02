package com.sairam.cabBookingApp.services.implementations;

import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.CustomerDetailsResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.DriverDetailsResponse;
import com.sairam.cabBookingApp.exceptions.CustomerNotFoundException;
import com.sairam.cabBookingApp.exceptions.DriverNotFoundException;
import com.sairam.cabBookingApp.exceptions.UserAlreadyExistsException;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Driver;
import com.sairam.cabBookingApp.models.Token;
import com.sairam.cabBookingApp.models.TripBook;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.services.CustomerService;
import com.sairam.cabBookingApp.services.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (customerRepository.findCustomerByEmail(customer.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "Customer already exists with this email " + customer.getEmail());
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        String jwtToken=jwtService.generateToken(customer);
        String refreshToken=jwtService.generateRefreshToken(customer);
        customer.setToken(Token.builder().tokenString(jwtToken).expired(false).revoked(false).build());
        customerRepository.save(customer);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public CustomerDetailsResponse getCustomerDetails(Long id) {
        Customer customer = customerRepository.findCustomerById(id).orElseThrow(
                () -> new DriverNotFoundException("Driver Not Found with this id: " + id)
        );
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, CustomerDetailsResponse.class);
    }

    @Override
    public AuthenticationResponse updateCustomerDetails(Customer newCustomer) {
        Customer customer=customerRepository.findCustomerById(newCustomer.getUserId()).
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
        customerRepository.save(customer);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public List<TripBook> getAllTripBooks() {
        String userEmail= SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer=customerRepository.findCustomerByEmail(userEmail).orElseThrow(
                ()->new CustomerNotFoundException("Customer Not Found with this mail: "+userEmail)
        );


        return customer.getTrips();
    }

    @Override
    public String deleteCustomer(Long id) {
        Customer customer=customerRepository.findCustomerById(id).
                orElseThrow(()-> new CustomerNotFoundException("Customer Not found with id: "+id));
        customerRepository.deleteById(id);
        return "Deleted Successfully";
    }

}

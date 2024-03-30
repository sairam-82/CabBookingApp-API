package com.sairam.cabBookingApp.services.implementations;

import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer register(Customer customer){
        customerRepository.save(customer);
        System.out.println(customer.getFirstName());
        String s=customer.getFirstName();
        Customer c=customerRepository.findCustomerByFirstName(s);
        return c;
    }

}

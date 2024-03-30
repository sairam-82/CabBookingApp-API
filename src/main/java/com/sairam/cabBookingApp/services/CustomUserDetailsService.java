package com.sairam.cabBookingApp.services;

import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
 @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("inside uds");
        UserDetails user=customerRepository.findCustomerByFirstName(username);
        System.out.println(user.getUsername());
        try{
        return user;}
        catch (Exception e){
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}

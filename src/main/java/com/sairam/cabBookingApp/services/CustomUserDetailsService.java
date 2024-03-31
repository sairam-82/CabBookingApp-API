package com.sairam.cabBookingApp.services;

import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
 @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("inside uds");
//        System.out.println(username);
//        System.out.println(" sairam " +customerRepository.findCustomerByEmail(username).getPassword());
        UserDetails user=customerRepository.findCustomerByEmail(username);
//        System.out.println(user.getAuthorities());
        return user;
//        System.out.println("done ");
//        System.out.println(user.getAuthorities());
//        try{
//        return user;}
//        catch (Exception e){
//            System.out.println("in exc");
//            throw new UsernameNotFoundException(e.getMessage());
//        }
    }
}

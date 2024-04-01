package com.sairam.cabBookingApp.repositories;

import com.sairam.cabBookingApp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.plaf.SpinnerUI;
import java.util.Optional;

@Service
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select p from Customer p where p.email=:email ")
    Optional<Customer> findCustomerByEmail(@Param("email")String email);

    @Query("select p from Customer p where p.userId=:id ")
    Optional<Customer> findCustomerById(@Param("id")Long id);

}

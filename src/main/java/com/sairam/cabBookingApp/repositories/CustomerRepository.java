package com.sairam.cabBookingApp.repositories;

import com.sairam.cabBookingApp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.SpinnerUI;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    @Query("select p from Customer p where p.firstName=:firstname ")
    public Customer findCustomerByFirstName(@Param("firstname")String firstname);
}

package com.sairam.cabBookingApp.repositories;

import com.sairam.cabBookingApp.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepository extends JpaRepository<Driver,String> {

    @Query("select p from Driver p where p.email= :email")
    Driver findDriverByEmail(String email);
}

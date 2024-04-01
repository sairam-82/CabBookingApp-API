package com.sairam.cabBookingApp.repositories;

import com.sairam.cabBookingApp.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,String> {

    @Query("select p from Driver p where p.email= :email")
    Optional<Driver> findDriverByEmail(String email);
    @Query("select p from Driver p where p.userId= :id")
    Optional<Driver> findDriverById(Long id);
}

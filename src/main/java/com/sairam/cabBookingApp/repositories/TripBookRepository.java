package com.sairam.cabBookingApp.repositories;

import com.sairam.cabBookingApp.models.TripBook;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TripBookRepository extends JpaRepository<TripBook,Long> {

    @Query("select t from TripBook t where t.tripId= :id")
    Optional<TripBook> findTripBookById(Long id);
}

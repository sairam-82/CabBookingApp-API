package com.sairam.cabBookingApp.repositories;

import com.sairam.cabBookingApp.models.TripBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripBookRepository extends JpaRepository<TripBook,String> {
}

package com.sairam.cabBookingApp.repositories;

import com.sairam.cabBookingApp.models.Simple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleRepository {
    public Simple findByName(String name);
}

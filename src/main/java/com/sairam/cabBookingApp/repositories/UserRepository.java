package com.sairam.cabBookingApp.repositories;

import com.sairam.cabBookingApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {


}

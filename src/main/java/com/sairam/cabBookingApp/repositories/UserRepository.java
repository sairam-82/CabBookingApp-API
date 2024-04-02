package com.sairam.cabBookingApp.repositories;

import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Token;
import com.sairam.cabBookingApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    @Query("select u from User u where u.token.tokenString= :jwt")
    Optional<User> findByToken(String jwt);

    @Query("select p from User p where p.email=:email ")
    Optional<User> findUserByEmail(String email);

    @Query("select p from User p where p.userId=:id ")
    Optional<User> findUserById(@Param("id")Long id);


}

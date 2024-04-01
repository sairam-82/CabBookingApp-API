package com.sairam.cabBookingApp.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    public String generateToken(UserDetails userDetails);
    public String generateRefreshToken(UserDetails userDetails);
}

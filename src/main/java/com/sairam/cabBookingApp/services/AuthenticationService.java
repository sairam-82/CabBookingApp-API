package com.sairam.cabBookingApp.services;

import com.sairam.cabBookingApp.controllers.exchanges.requests.AuthenticationRequest;
import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService{
    AuthenticationResponse authenticate(AuthenticationRequest authRequest);
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;

}


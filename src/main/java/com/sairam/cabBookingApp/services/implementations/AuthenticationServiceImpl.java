package com.sairam.cabBookingApp.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sairam.cabBookingApp.controllers.exchanges.requests.AuthenticationRequest;
import com.sairam.cabBookingApp.controllers.exchanges.responses.AuthenticationResponse;
import com.sairam.cabBookingApp.models.Token;
import com.sairam.cabBookingApp.models.User;
import com.sairam.cabBookingApp.repositories.UserRepository;
import com.sairam.cabBookingApp.services.AuthenticationService;
import com.sairam.cabBookingApp.services.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationServiceImpl  implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        Token token=saveUserToken(user, jwtToken);
        user.setToken(token);
        userRepository.save(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private Token saveUserToken(User user, String jwtToken) {
        return Token.builder()
                .tokenString(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
    }


    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findUserByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                Token token=saveUserToken(user, accessToken);
                user.setToken(token);
                userRepository.save(user);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

}

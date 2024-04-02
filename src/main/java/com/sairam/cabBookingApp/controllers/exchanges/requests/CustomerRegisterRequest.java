package com.sairam.cabBookingApp.controllers.exchanges.requests;

import com.sairam.cabBookingApp.models.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class CustomerRegisterRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String role;
    private String address;
    private Long mobileNumber;
    @Embedded
    private Token token;
}

package com.sairam.cabBookingApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sairam.cabBookingApp.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    private String id;
    @NotNull
    @Column(name="first_name")
    private String firstName;
    @NotNull
    private String lastName;
    @Email@NotNull
    private String email;
    @NotNull
    private String password;

    @NotNull
    private Role role;



    private String address;


    private Long mobileNumber;






}

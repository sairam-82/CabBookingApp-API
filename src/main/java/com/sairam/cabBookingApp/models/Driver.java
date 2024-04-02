package com.sairam.cabBookingApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sairam.cabBookingApp.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Driver extends User{
    public Driver(@NotNull String firstName, @NotNull String lastName, @Email @NotNull String email, @NotNull String password, @NotNull String role, String address, Long mobileNumber, String license) {
        super( firstName, lastName, email, password, role,address, mobileNumber,null);
        this.license = license;


    }





    private int numberOfRatings;

    private String license;
    @OneToMany(mappedBy = "driver",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<TripBook> driverTrips;

    @Embedded
    private Cab cab;

    private Double rating;



}

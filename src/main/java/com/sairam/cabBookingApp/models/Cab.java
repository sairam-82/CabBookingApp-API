package com.sairam.cabBookingApp.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Cab {

    public String cabType;

    public String numberPlate;

    public Double perKmRate;

}

package com.sairam.cabBookingApp.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Simple {
    private String name;
    private String Id;
    private String password;

}

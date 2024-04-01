package com.sairam.cabBookingApp.controllers.exchanges.responses;

import com.sairam.cabBookingApp.models.Cab;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@Builder
@NoArgsConstructor
public class DriverDetailsResponse {
    private Long userId;
    private Cab cab;
    private String address;
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String cabType;
    ;
}

package com.sairam.cabBookingApp.controllers.exchanges.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TripBookRegisterRequest {

    private Long customerId;
    private Long driverId;
    private String toAddress;
    private String fromAddress;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private Long distanceInKms;
    private Long billAmount;
}

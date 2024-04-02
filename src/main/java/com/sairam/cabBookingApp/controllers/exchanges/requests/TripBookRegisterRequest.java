package com.sairam.cabBookingApp.controllers.exchanges.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotBlank
    private Long customerId;
    @NotNull
    @NotBlank
    private Long driverId;
    private String toAddress;
    private String fromAddress;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    @NotBlank
    @NotNull
    private Long distanceInKms;
    private Long billAmount;
}

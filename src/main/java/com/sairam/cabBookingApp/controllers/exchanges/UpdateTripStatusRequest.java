package com.sairam.cabBookingApp.controllers.exchanges;

import com.sairam.cabBookingApp.models.enums.Status;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class UpdateTripStatusRequest {

    private Status status;
    @NotNull
    @NotBlank
    private Long TripId;
}

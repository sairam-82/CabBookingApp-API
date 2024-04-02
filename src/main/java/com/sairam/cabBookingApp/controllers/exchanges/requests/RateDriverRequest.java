package com.sairam.cabBookingApp.controllers.exchanges.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class RateDriverRequest {
    private Long tripId;
    private Double ratingValue;
}

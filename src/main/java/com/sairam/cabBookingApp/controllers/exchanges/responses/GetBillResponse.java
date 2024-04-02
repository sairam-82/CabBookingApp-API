package com.sairam.cabBookingApp.controllers.exchanges.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class GetBillResponse {
    private Long distanceInKms;

    private Long billAmount;
    private Long tripId;
}

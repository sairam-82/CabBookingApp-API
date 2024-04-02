package com.sairam.cabBookingApp.controllers.exchanges.responses;

import com.sairam.cabBookingApp.models.TripBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor@NoArgsConstructor
public class GetTripBooksResponse {
    private List<TripBook> getTripBooksResponse;
}

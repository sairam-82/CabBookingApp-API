package com.sairam.cabBookingApp.services;

import com.sairam.cabBookingApp.controllers.exchanges.UpdateTripStatusRequest;
import com.sairam.cabBookingApp.controllers.exchanges.requests.RateDriverRequest;
import com.sairam.cabBookingApp.controllers.exchanges.requests.TripBookRegisterRequest;
import com.sairam.cabBookingApp.controllers.exchanges.responses.GetBillResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.GetTripBooksResponse;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.TripBook;

import java.time.LocalDate;
import java.util.List;

public interface TripBookService {

    String addTrip(TripBookRegisterRequest tripBookRegisterRequest);
    String rateDriver(RateDriverRequest rateDriverRequest);

    String updateTripStatus(UpdateTripStatusRequest updateTripStatusRequest);
    GetBillResponse getBillDetails(Long id);

    GetTripBooksResponse getAllTrips();
    GetTripBooksResponse getTripsOnDate(LocalDate date);

    String deleteTrip(Long id);



}


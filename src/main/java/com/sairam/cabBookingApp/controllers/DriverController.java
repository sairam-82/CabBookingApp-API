package com.sairam.cabBookingApp.controllers;


import com.sairam.cabBookingApp.controllers.exchanges.UpdateTripStatusRequest;
import com.sairam.cabBookingApp.controllers.exchanges.requests.AddCabRequest;
import com.sairam.cabBookingApp.controllers.exchanges.responses.DriverDetailsResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.GetBillResponse;
import com.sairam.cabBookingApp.models.Cab;
import com.sairam.cabBookingApp.repositories.DriverRepository;
import com.sairam.cabBookingApp.services.DriverService;
import com.sairam.cabBookingApp.services.TripBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private TripBookService tripBookService;

    @PutMapping("/driver/updateCab")
    public String updateCab(@RequestBody Cab cab){
         return driverService.addCab(cab);

    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<DriverDetailsResponse> getDriver(@PathVariable Long driverId){
        return ResponseEntity.ok().body(driverService.getDriverDetails(driverId));
    }

    @PutMapping("/driver/trip-status")
    public ResponseEntity<String> updateTripStatus(@RequestBody UpdateTripStatusRequest updateTripStatusRequest){
        return ResponseEntity.ok().body(tripBookService.updateTripStatus(updateTripStatusRequest));
    }

    @GetMapping("/driver/trip-booking/{tripId}/bill")
    public ResponseEntity<GetBillResponse> getBillDetails(@PathVariable Long tripId){
        return ResponseEntity.ok().body(tripBookService.getBillDetails(tripId));

    }

    @DeleteMapping("/drivers/{driverId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long driverId){
        return ResponseEntity.ok().body(driverService.deleteDriver(driverId));
    }


}

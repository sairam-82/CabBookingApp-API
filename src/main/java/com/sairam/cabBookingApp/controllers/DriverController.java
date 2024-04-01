package com.sairam.cabBookingApp.controllers;


import com.sairam.cabBookingApp.controllers.exchanges.requests.AddCabRequest;
import com.sairam.cabBookingApp.controllers.exchanges.responses.DriverDetailsResponse;
import com.sairam.cabBookingApp.models.Cab;
import com.sairam.cabBookingApp.repositories.DriverRepository;
import com.sairam.cabBookingApp.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PutMapping("/driver/updateCab")
    public String updateCab(@RequestBody Cab cab){
         return driverService.addCab(cab);

    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<DriverDetailsResponse> getDriver(@PathVariable Long driverId){
        return ResponseEntity.ok().body(driverService.getDriverDetails(driverId));
    }


}

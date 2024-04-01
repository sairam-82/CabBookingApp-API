package com.sairam.cabBookingApp.services.implementations;

import com.sairam.cabBookingApp.controllers.exchanges.requests.TripBookRegisterRequest;
import com.sairam.cabBookingApp.exceptions.CustomerNotFoundException;
import com.sairam.cabBookingApp.exceptions.DriverNotFoundException;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Driver;
import com.sairam.cabBookingApp.models.TripBook;
import com.sairam.cabBookingApp.models.User;
import com.sairam.cabBookingApp.models.enums.Status;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.repositories.DriverRepository;
import com.sairam.cabBookingApp.repositories.TripBookRepository;
import com.sairam.cabBookingApp.services.TripBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripBookServiceImpl implements TripBookService {

    @Autowired
    private TripBookRepository tripBookRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Override
    public String addTrip(TripBookRegisterRequest trip) {
        Driver driver=driverRepository.findDriverById(trip.getDriverId()).orElseThrow(()-> {
            return new DriverNotFoundException("Driver Not found with this id: " + trip.getDriverId());
        });
        Customer customer=customerRepository.findCustomerById(trip.getCustomerId()).
                orElseThrow(()->new CustomerNotFoundException("Customer Not Found with id: "+
                        trip.getCustomerId()));
        TripBook tripBook=TripBook.builder().arrivalDateTime(trip.getArrivalDateTime()).
                departureDateTime(trip.getDepartureDateTime()).billAmount((driver.getCab().getPerKmRate())*trip.getDistanceInKms())
                .status(Status.NOT_STARTED).toAddress(trip.getToAddress()).fromAddress(trip.getFromAddress())
                .customer(customer).driver(driver).build();
        tripBookRepository.save(tripBook);
        return "success";
    }

//    public String checkTrip(){
//        List<TripBook> trips=customerRepository.findCustomerById(53L).getTrips();
//        List<TripBook> tri=driverRepository.findDriverById(52L).getDriverTrips();
//
//
//
//        System.out.println(trips.size());
//        var x="";
//        for(TripBook trip:trips){
//            x=tri.get(0).getStatus().name()+"  "+trip.getTripId();
//        }
////        System.out.println(x);
//        return x;
//    }
}

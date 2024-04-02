package com.sairam.cabBookingApp.services.implementations;

import com.sairam.cabBookingApp.controllers.exchanges.UpdateTripStatusRequest;
import com.sairam.cabBookingApp.controllers.exchanges.requests.RateDriverRequest;
import com.sairam.cabBookingApp.controllers.exchanges.requests.TripBookRegisterRequest;
import com.sairam.cabBookingApp.controllers.exchanges.responses.GetBillResponse;
import com.sairam.cabBookingApp.controllers.exchanges.responses.GetTripBooksResponse;
import com.sairam.cabBookingApp.exceptions.CabNotFoundException;
import com.sairam.cabBookingApp.exceptions.CustomerNotFoundException;
import com.sairam.cabBookingApp.exceptions.DriverNotFoundException;
import com.sairam.cabBookingApp.exceptions.TripNotFoundException;
import com.sairam.cabBookingApp.models.Customer;
import com.sairam.cabBookingApp.models.Driver;
import com.sairam.cabBookingApp.models.TripBook;
import com.sairam.cabBookingApp.models.User;
import com.sairam.cabBookingApp.models.enums.Status;
import com.sairam.cabBookingApp.repositories.CustomerRepository;
import com.sairam.cabBookingApp.repositories.DriverRepository;
import com.sairam.cabBookingApp.repositories.TripBookRepository;
import com.sairam.cabBookingApp.services.TripBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        if(driver.getCab()==null){
            throw new CabNotFoundException("Cab Not Found for this Driver with id: "+driver.getUserId());
        }
        TripBook tripBook=TripBook.builder().
                arrivalDateTime(trip.getArrivalDateTime()).
                departureDateTime(trip.getDepartureDateTime()).
                billAmount((driver.getCab().getPerKmRate())*trip.getDistanceInKms()).
                status(Status.NOT_STARTED).
                toAddress(trip.getToAddress()).
                fromAddress(trip.getFromAddress()).
                customer(customer).
                distanceInKms(trip.getDistanceInKms()).
                driver(driver).
                build();
        TripBook persistedTripBook=tripBookRepository.save(tripBook);
        return "TripBooked successfully with id: "+persistedTripBook.getTripId();
    }

    @Override
    public String rateDriver(RateDriverRequest rateDriverRequest) {
        TripBook tripBook=tripBookRepository.findTripBookById(rateDriverRequest.getTripId()).
                orElseThrow(()-> new TripNotFoundException("Trip Not Found with id: "+rateDriverRequest.getTripId()));
        Driver driver=tripBook.getDriver();
        if (driver.getNumberOfRatings()==0) driver.setRating(rateDriverRequest.getRatingValue());
        else driver.setRating((driver.getRating()+rateDriverRequest.getRatingValue())/driver.getNumberOfRatings());
        tripBook.setDriver(driver);
        tripBookRepository.save(tripBook);
        return "Thanks for your valuable rating";
    }

    @Override
    public String updateTripStatus(UpdateTripStatusRequest tripStatus) {
        TripBook tripBook=tripBookRepository.findTripBookById(tripStatus.getTripId())
                .orElseThrow(()-> new TripNotFoundException("Trip Not Found with id: "+tripStatus.getTripId()));
//
        tripBook.setStatus(tripStatus.getStatus());
        tripBookRepository.save(tripBook);
        return "Update the status of Trip with trip id: "+tripStatus.getTripId()+" with "+
                tripStatus.getStatus().name();


    }

    @Override
    public GetBillResponse getBillDetails(Long id) {
        TripBook tripBook=tripBookRepository.findTripBookById(id)
                .orElseThrow(()-> new TripNotFoundException("Trip Not Found with id: "+id));
        ModelMapper modelMapper= new ModelMapper();
        return modelMapper.map(tripBook,GetBillResponse.class);
    }

    @Override
    public GetTripBooksResponse getAllTrips() {
        return new GetTripBooksResponse(tripBookRepository.findAll());
    }

    @Override
    public GetTripBooksResponse getTripsOnDate(LocalDate date) {
        GetTripBooksResponse getTripBooksResponse=getAllTrips();
        List<TripBook> trips=getTripBooksResponse.getGetTripBooksResponse().stream().
                filter(tripBook -> tripBook.getDepartureDateTime().toLocalDate().equals(date))
                .toList();
        getTripBooksResponse.setGetTripBooksResponse(trips);
        return getTripBooksResponse;
    }

    @Override
    public String deleteTrip(Long id) {
        TripBook tripBook=tripBookRepository.findTripBookById(id)
                .orElseThrow(()-> new TripNotFoundException("Trip Not Found with id: "+id));
        if( tripBook.getStatus()==Status.COMPLETED || tripBook.getStatus()==Status.CANCELLED)
            tripBookRepository.deleteById(id);
        else return "Cannot delete Trip. Its neither completed not cancelled";
        return "Deleted the trip Successfully";
    }


}

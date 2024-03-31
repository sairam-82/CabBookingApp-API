package com.sairam.cabBookingApp.models;

import com.sairam.cabBookingApp.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.tool.schema.spi.SchemaTruncator;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor

public class TripBook {

    @ManyToOne
    @JoinColumn(name = "trips")
    @NotNull
    private Customer customer;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tripId;

    @ManyToOne
    @JoinColumn(name = "driverTrips")
    private Driver driver;

    private String toAddress;

    private String fromAddress;

    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;


    private Status status;

    private Double distanceInKms;

    private String billAmount;

}

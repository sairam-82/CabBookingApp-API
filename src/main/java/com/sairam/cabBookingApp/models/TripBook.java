package com.sairam.cabBookingApp.models;

import com.sairam.cabBookingApp.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.tool.schema.spi.SchemaTruncator;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Builder
@Data@AllArgsConstructor@NoArgsConstructor

public class TripBook {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_user_id")
    @NotNull
//    @Column(name="customer_user_id")
    private Customer customer;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tripId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_user_id")
//    @Column(name="driver_user_id")
    private Driver driver;

    private String toAddress;

    private String fromAddress;

    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;



    @Enumerated(EnumType.STRING)
    private Status status;

    private Long distanceInKms;

    private Long billAmount;

}

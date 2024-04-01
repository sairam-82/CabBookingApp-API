package com.sairam.cabBookingApp.models.enums;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;


public enum Status {
    COMPLETED,ONGOING,STARTED,CANCELLED,POSTPONED,PREPONED,NOT_STARTED;

}

package com.sairam.cabBookingApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundHandler(CustomerNotFoundException e, WebRequest re){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), e.getMessage(), re.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundHandler(DriverNotFoundException e, WebRequest re){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), e.getMessage(), re.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CabNotFoundException.class)
    public ResponseEntity<ErrorDetails> cabNotFoundHandler(CabNotFoundException e, WebRequest re){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), e.getMessage(), re.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<ErrorDetails> tripNotFoundHandler(TripNotFoundException e, WebRequest re){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), e.getMessage(), re.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundHandler(UserNotFoundException e, WebRequest re){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), e.getMessage(), re.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> userExistsHandler(UserAlreadyExistsException e, WebRequest re){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), e.getMessage(), re.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TripStatusException.class)
    public ResponseEntity<ErrorDetails> tripStatusHandler(TripNotFoundException e, WebRequest re){
        ErrorDetails err = new ErrorDetails(LocalDateTime.now(), e.getMessage(), re.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }
}

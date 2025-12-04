package com.mycompany.dto;

import com.mycompany.model.enums.FlightType;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
public record FlightDTO(

        Long id,

        @NotBlank(message = "Airline name is required")
        String airlineName,

        @NotBlank(message = "Flight number is required")
        String flightNumber,

        @NotBlank(message = "Origin is required")
        String origin,

        @NotBlank(message = "Destination is required")
        String destination,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        Double price,

        @NotNull(message = "Departure time is required")
        @Future(message = "Departure time must be in the future")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime departureTime,

        @NotNull(message = "Arrival time is required")
        @Future(message = "Arrival time must be in the future")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime arrivalTime,

        @NotNull(message = "Total seats is required")
        @Min(value = 1, message = "There must be at least 1 seat")
        Integer TotalSeats,

        Integer ReservedSeat,

        @NotNull(message = "Flight type is required")
        FlightType flightType
) {}
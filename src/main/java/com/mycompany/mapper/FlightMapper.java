package com.mycompany.mapper;

import com.mycompany.dto.FlightDTO;
import com.mycompany.model.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDTO toDto(Flight flight) {
        return FlightDTO.builder()
                .airlineName(flight.getAirlineName())
                .flightNumber(flight.getFlightNumber())
                .origin(flight.getOrigin())
                .destination(flight.getDestination())
                .price(flight.getPrice())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .availableSeats(flight.getAvailableSeats())
                .flightType(flight.getFlightType())
                .build();
    }

    public Flight toEntity(FlightDTO dto) {
        return Flight.builder()
                .airlineName(dto.getAirlineName())
                .flightNumber(dto.getFlightNumber())
                .origin(dto.getOrigin())
                .destination(dto.getDestination())
                .price(dto.getPrice())
                .departureTime(dto.getDepartureTime())
                .arrivalTime(dto.getArrivalTime())
                .availableSeats(dto.getAvailableSeats())
                .flightType(dto.getFlightType())
                .build();
    }
}

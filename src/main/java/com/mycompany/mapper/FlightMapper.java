package com.mycompany.mapper;

import com.mycompany.dto.FlightDTO;
import com.mycompany.model.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDTO toDto(Flight flight) {
        return new FlightDTO(
                flight.getId(),
                flight.getAirlineName(),
                flight.getFlightNumber(),
                flight.getOrigin(),
                flight.getDestination(),
                flight.getPrice(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getTotalSeats(),
                flight.getReservedSeats(),
                flight.getFlightType()
        );
    }

    public Flight toEntity(FlightDTO dto) {
        return Flight.builder()
                .airlineName(dto.airlineName())
                .flightNumber(dto.flightNumber())
                .origin(dto.origin())
                .destination(dto.destination())
                .price(dto.price())
                .departureTime(dto.departureTime())
                .arrivalTime(dto.arrivalTime())
                .totalSeats(dto.totalSeats())
                .reservedSeats(dto.reservedSeats())
                .flightType(dto.flightType())
                .build();
    }
}


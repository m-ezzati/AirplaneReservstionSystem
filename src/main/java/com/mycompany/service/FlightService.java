package com.mycompany.service;

import com.mycompany.dto.FlightDTO;
import com.mycompany.mapper.FlightMapper;
import com.mycompany.model.Flight;
import com.mycompany.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper mapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = mapper;
    }

    public Flight saveFlight(FlightDTO flightDto) {

        if (flightDto.departureTime().isAfter(flightDto.arrivalTime())) {
            throw new IllegalArgumentException("Departure time must be before arrival time.");
        }

        if (flightDto.totalSeats() <= 0) {
            throw new IllegalArgumentException("Total seats must be positive.");
        }

        if (flightDto.price() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        if (flightDto.origin().equalsIgnoreCase(flightDto.destination())) {
            throw new IllegalArgumentException("Origin and destination cannot be the same.");
        }

        return flightRepository.save(flightMapper.toEntity(flightDto));
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public List<Flight> findByOriginAndDestination(String origin, String destination) {
        return flightRepository.findByOriginAndDestination(origin, destination);
    }

    public List<Flight> findByOriginAndDestinationAndDepartureDate(String origin, String destination, LocalDateTime departureTime) {
        return flightRepository.findByOriginAndDestinationAndDepartureTimeAfter(origin, destination, departureTime);
    }

}

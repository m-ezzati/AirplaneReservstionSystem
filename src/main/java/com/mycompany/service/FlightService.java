package com.mycompany.service;

import com.mycompany.dto.FlightDTO;
import com.mycompany.mapper.FlightMapper;
import com.mycompany.model.Flight;
import com.mycompany.repository.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private static final Logger log = LoggerFactory.getLogger(FlightService.class);

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper mapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = mapper;
    }

    public Flight saveFlight(FlightDTO flightDto) {

        log.info("Saving new flight: origin={} destination={} departure={} arrival={}",
                flightDto.origin(), flightDto.destination(),
                flightDto.departureTime(), flightDto.arrivalTime());

        if (flightDto.departureTime().isAfter(flightDto.arrivalTime())) {
            log.warn("Invalid flight time: departure={} arrival={} ",
                    flightDto.departureTime(), flightDto.arrivalTime());

            throw new IllegalArgumentException("Departure time must be before arrival time.");
        }

        if (flightDto.totalSeats() <= 0) {
            log.warn("Invalid seat count: {} ", flightDto.totalSeats());
            throw new IllegalArgumentException("Total seats must be positive.");
        }

        if (flightDto.price() <= 0) {
            log.warn("Invalid price: {} ", flightDto.price());
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        if (flightDto.origin().equalsIgnoreCase(flightDto.destination())) {
            log.warn("Origin and destination are the same: {}", flightDto.origin());
            throw new IllegalArgumentException("Origin and destination cannot be the same.");
        }

        Flight saved =  flightRepository.save(flightMapper.toEntity(flightDto));

        log.info("Flight saved successfully with id={}", saved.getId());

        return saved;
    }

    public List<Flight> getAllFlights() {
        log.info("Fetching all flights");
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        log.info("Fetching flight with id={}", id);
        return flightRepository.findById(id);
    }

    public List<Flight> findByOriginAndDestination(String origin, String destination) {
        log.info("Searching flights origin={} destination={}", origin, destination);
        return flightRepository.findByOriginAndDestination(origin, destination);
    }

    public List<Flight> findByOriginAndDestinationAndDepartureDate(String origin, String destination, LocalDateTime departureTime) {
        log.info("Searching flights origin={} destination={} departureTimeAfter={}",
                origin, destination, departureTime);
        return flightRepository.findByOriginAndDestinationAndDepartureTimeAfter(origin, destination, departureTime);
    }

}

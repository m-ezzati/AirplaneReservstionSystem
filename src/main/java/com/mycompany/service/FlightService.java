package com.mycompany.service;

import com.mycompany.model.Flight;
import com.mycompany.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id){
        return flightRepository.findById(id);
    }

    public List<Flight> findByOriginAndDestination(String origin, String destination) {
        return flightRepository.findByOriginAndDestination(origin, destination);
    }

}

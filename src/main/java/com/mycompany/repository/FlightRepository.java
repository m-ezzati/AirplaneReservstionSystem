package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.model.Flight;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOriginAndDestination(String origin, String destination);
    List<Flight> findByOriginAndDestinationAndDepartureTimeAfter(String origin, String destination, LocalDateTime departureTime);
}

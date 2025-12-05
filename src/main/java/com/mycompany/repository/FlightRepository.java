package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOriginAndDestination(String origin, String destination);
}

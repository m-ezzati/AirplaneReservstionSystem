package com.mycompany.controller;

import com.mycompany.dto.FlightDTO;
import com.mycompany.mapper.FlightMapper;
import com.mycompany.model.Flight;
import com.mycompany.repository.FlightRepository;
import com.mycompany.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("flights")
public class FlightController {
    private final FlightService flightService;
    private final FlightMapper flightMapper;

    public FlightController(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    @GetMapping("search")
    public String searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
//            @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime departureDate,
            Model model
            ){

        List<FlightDTO> results = flightService.findByOriginAndDestination(origin, destination)
                .stream()
                .map(flightMapper::toDto)
                .toList();

        model.addAttribute("results", results);

        return "results";

    }

}

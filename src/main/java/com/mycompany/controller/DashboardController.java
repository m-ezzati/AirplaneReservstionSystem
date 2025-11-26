package com.mycompany.controller;

import com.mycompany.dto.FlightDTO;
import com.mycompany.mapper.FlightMapper;
import com.mycompany.model.Flight;
import com.mycompany.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final FlightService flightService;
    private final FlightMapper flightMapper;

    public DashboardController(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }

    @GetMapping
    public String showDashboard(Model model) {
        List<Flight> flights = flightService.getAllFlights();

        List<FlightDTO> flightDTOs = flights.stream()
                .map(flightMapper::toDto)
                .toList();

        model.addAttribute("flights", flightDTOs);

        return "dashboard";
    }

    @PostMapping("/add-flight")
    @PreAuthorize("hasRole('ADMIN')")
    public String addFlight(@ModelAttribute @Valid FlightDTO dto,  BindingResult result) {
        if (result.hasErrors()) {
            return "add-flight";
        }
        flightService.saveFlight(flightMapper.toEntity(dto));

        return "redirect:/dashboard";
    }

}

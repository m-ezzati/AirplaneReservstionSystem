package com.mycompany.controller;

import com.mycompany.model.Booking;
import com.mycompany.model.Flight;
import com.mycompany.model.User;
import com.mycompany.service.BookingService;
import com.mycompany.service.FlightService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final FlightService flightService;

    public BookingController(BookingService bookingService, FlightService flightService) {
        this.bookingService = bookingService;
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public String addBooking(@RequestParam Long flightId,
                             HttpSession session,
                             Model model)
    {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Flight flight = flightService.getFlightById(flightId)
                .orElseThrow(()->new EntityNotFoundException("flight not found"));

        try {

            Booking booking = new Booking();
            booking.setFlight(flight);
            booking.setUser(user);
            bookingService.save(booking);
            return "redirect:/user/dashboard";
        }catch (IllegalStateException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("flight", flight);
            return "search";
        }
    }

}

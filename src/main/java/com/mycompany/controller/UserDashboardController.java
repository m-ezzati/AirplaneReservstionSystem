package com.mycompany.controller;

import com.mycompany.model.Booking;
import com.mycompany.model.User;
import com.mycompany.service.BookingService;
import jakarta.servlet.http.HttpSession;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/dashboard")
public class UserDashboardController {

    private final BookingService bookingService;

    public UserDashboardController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String showUserDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        List<Booking> bookings = bookingService.findUserBookingByUserId(user.getId());

        model.addAttribute("user",user);
        model.addAttribute("bookings", bookings);

        return "userDashboard";
    }
}

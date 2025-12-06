package com.mycompany.service;

import com.mycompany.model.Booking;
import com.mycompany.model.Flight;
import com.mycompany.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void save(Booking booking) {

        Flight flight = booking.getFlight();
        if (!flight.hasCapacity()) {
            throw new IllegalStateException("No available seats!");
        }

        flight.setReservedSeats(flight.getReservedSeats() + 1);

        bookingRepository.save(booking);
    }

    public List<Booking> findUserBookingByUserId(Long userId) {
        return bookingRepository.findUserBookingByUserId(userId);
    }


}

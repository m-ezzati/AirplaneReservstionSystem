package com.mycompany.service;

import com.mycompany.model.Booking;
import com.mycompany.model.Flight;
import com.mycompany.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public void save(Booking booking) {

        log.info("Saving booking for userId={} flightId={}",
                booking.getUser().getId(),
                booking.getFlight().getId());

        Flight flight = booking.getFlight();
        if (!flight.hasCapacity()) {
            log.warn("Booking rejected! No capacity for flightId={}", flight.getId());

            throw new IllegalStateException("No available seats!");
        }

        flight.setReservedSeats(flight.getReservedSeats() + 1);

        bookingRepository.save(booking);

        log.info("Booking saved successfully for userId={} flightId={}",
                booking.getUser().getId(),
                flight.getId());
    }

    public List<Booking> findUserBookingByUserId(Long userId) {
        log.info("Fetching bookings for userId={}", userId);

        List<Booking> bookings = bookingRepository.findUserBookingByUserId(userId);

        log.debug("Found {} bookings for userId={}", bookings.size(), userId);

        return bookings;

    }


}

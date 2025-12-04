package com.mycompany.service;

import com.mycompany.model.Booking;
import com.mycompany.repository.BookingRepository;

import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking save(Booking booking){
        return bookingRepository.save(booking);
    }

    public List<Booking> findUserBookingByUserId(Long userId){
        return bookingRepository.findUserBookingByUserId(userId);
    }


}

package com.yorbit.moviebooking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yorbit.moviebooking.model.Booking;

public interface BookingService {
	Page<Booking> getAllBookings(Pageable paging);

    Booking getBookingById(Integer id);

    Booking saveBooking(Booking newBooking);

    Booking updateBooking(Booking updatedBooking, Integer id);

    void deleteBookingById(Integer id);
}

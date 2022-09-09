package com.yorbit.moviebooking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yorbit.moviebooking.model.Booking;
import com.yorbit.moviebooking.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	private static final Logger LOGGER = Logger.getLogger(BookingController.class);

    @GetMapping("/bookings")
    public ResponseEntity<Map<String, Object>> getAllBookings(
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
    	LOGGER.info("Retrieving all bookings.");
    	List<Booking> bookings = new ArrayList<>();
    	Pageable paging = PageRequest.of(page, size);
        Page<Booking> pageBookings = bookingService.getAllBookings(paging);
        
        bookings = pageBookings.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("cinemas", bookings);
        response.put("currentPage", pageBookings.getNumber());
        response.put("totalItems", pageBookings.getTotalElements());
        response.put("totalPages", pageBookings.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/bookings")
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking) {
    	LOGGER.info("Saving a new booking with id:"+ booking.getId());
        Booking savedBooking = bookingService.saveBooking(booking);
        LOGGER.info("Booking saved successfully with id:"+ booking.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }
    
    @PutMapping("/bookings")
    public ResponseEntity<?> modifyBooking(@RequestBody Booking booking) {
    	LOGGER.info("Modifying the booking with id:"+booking.getId());
        Booking modifiedBooking = bookingService.updateBooking(booking, booking.getId());
        LOGGER.info("Booking modified successfully for id:"+ booking.getId());
        return ResponseEntity.status(HttpStatus.OK).body(modifiedBooking);
    }
    
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
    	LOGGER.info("Cancelling the booking with id:"+id);
    	bookingService.deleteBookingById(id);
    	LOGGER.info("Booking cancelled successfully for id:"+id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}

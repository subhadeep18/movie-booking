package com.yorbit.moviebooking.service.impl;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yorbit.moviebooking.exception.BookingNotFoundException;
import com.yorbit.moviebooking.model.Booking;
import com.yorbit.moviebooking.repository.BookingRepository;
import com.yorbit.moviebooking.service.BookingService;

@Service
@Transactional
@PropertySource("classpath:message.properties")
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);
	
	@Value("${bookingnotfound.message}")
	String errorMsg;
	
	@Override
	public Page<Booking> getAllBookings(Pageable paging) {
		return bookingRepository.findAll(paging);
	}

	@Override
	public Booking getBookingById(Long id) {
		return bookingRepository.getReferenceById(id);
	}

	@Override
	public Booking saveBooking(Booking newBooking) {
		return bookingRepository.save(newBooking);
	}

	@Override
	public Booking updateBooking(Booking updatedBooking, Long id) {
		if(bookingRepository.existsById(id)) {
			return bookingRepository.save(updatedBooking);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new BookingNotFoundException(errorMsg + id);
		}
	}

	@Override
	public void deleteBookingById(Long id) {
		if(bookingRepository.existsById(id)) {
			bookingRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new BookingNotFoundException(errorMsg + id);
		}
	}

}

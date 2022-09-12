package com.yorbit.moviebooking.service.impl;

import java.sql.SQLException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorbit.moviebooking.exception.BookingNotFoundException;
import com.yorbit.moviebooking.model.Booking;
import com.yorbit.moviebooking.repository.BookingRepository;
import com.yorbit.moviebooking.service.BookingService;

@Service
@PropertySource("classpath:message.properties")
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);
	
	@Value("${bookingnotfound.message}")
	String errorMsg;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Booking> getAllBookings(Pageable paging) {
		return bookingRepository.findAll(paging);
	}

	@Override
	@Transactional(readOnly = true)
	public Booking getBookingById(Long id) {
		return bookingRepository.getReferenceById(id);
	}

	@Override
	@Transactional(rollbackFor = { SQLException.class })
	public Booking saveBooking(Booking newBooking) {
		return bookingRepository.save(newBooking);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Booking updateBooking(Booking updatedBooking, Long id) {
		if(bookingRepository.existsById(id)) {
			return bookingRepository.updateBooking(updatedBooking.getBookedSeats(),
					updatedBooking.isActive(), id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new BookingNotFoundException(errorMsg + id);
		}
	}

	@Override
	@Transactional
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

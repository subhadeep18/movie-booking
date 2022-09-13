package com.yorbit.moviebooking.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorbit.moviebooking.exception.BookingNotFoundException;
import com.yorbit.moviebooking.exception.DbException;
import com.yorbit.moviebooking.model.Booking;
import com.yorbit.moviebooking.repository.BookingRepository;
import com.yorbit.moviebooking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);
	
	@Value("${globalexception.message}")
	String globalErrorMsg;
	
	@Value("${bookingnotfound.message}")
	String errorMsg;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Booking> getAllBookings(Pageable paging) {
		return bookingRepository.findAll(paging);
	}

	@Override
	@Transactional(readOnly = true)
	public Booking getBookingById(Integer id) {
		return bookingRepository.getReferenceById(id);
	}

	@Override
	@Transactional(rollbackFor = DbException.class )
	public Booking saveBooking(Booking newBooking) {
		Integer result = bookingRepository.saveBooking(newBooking.getId(), newBooking.getBookedSeats(),
				newBooking.isActive(), newBooking.getCinema().getId(), newBooking.getUser().getId());
		if(result == 1) {
			return newBooking;
		}
		else {
			throw new DbException(globalErrorMsg);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Booking updateBooking(Booking updatedBooking, Integer id) {
		if(bookingRepository.existsById(id)) {
			Integer result = bookingRepository.updateBooking(updatedBooking.getBookedSeats(),
					updatedBooking.isActive(), id);
			if(result == 1) {
				return updatedBooking;
			}
			else {
				throw new DbException(globalErrorMsg);
			}
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new BookingNotFoundException(errorMsg + id);
		}
	}

	@Override
	@Transactional
	public void deleteBookingById(Integer id) {
		if(bookingRepository.existsById(id)) {
			bookingRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new BookingNotFoundException(errorMsg + id);
		}
	}

}

package com.yorbit.moviebooking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yorbit.moviebooking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	Page<Booking> findAll(Pageable pageable);
	
	@Modifying
	@Query(value="INSERT into booking(booking_id, booked_seats, is_active, cinema_id, user_id) VALUES (?1, ?2, ?3, ?4, ?5);",
			nativeQuery=true)
	Booking saveBooking(Long id, Integer bookedSeats, Character isActive, Long cinemaId, Long userId);
	
	@Modifying
	@Query("UPDATE Booking b set b.bookedSeats=?1, b.isActive=?2 where b.id=?3")
	Booking updateBooking(Integer bookedSeats, Character isActive, Long id);
	
	@Modifying
	@Query("DELETE Booking b wher b.id=?1")
	void deleteById(Long id);
}

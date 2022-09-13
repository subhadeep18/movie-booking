package com.yorbit.moviebooking.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yorbit.moviebooking.model.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Integer>{
	Page<Cinema> findAll(Pageable pageable);
	
	@Modifying
	@Query(value="INSERT into cinema(cinema_id, booking_date, booking_time, isAvailable, name, seating_capacity, screen_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7);",
			nativeQuery=true)
	Integer saveCinema(Integer id, LocalDate bookingDate, LocalTime bookingTime, String isAvailable, String name, Integer seatingCapacity, Integer screen_id);
	
	@Modifying
	@Query("UPDATE Cinema c set c.seatingCapacity=?1, c.isAvailable=?2 where c.id=?3")
	Integer updateCinema(Integer seatingCapacity, String isAvailable, Integer id);
	
	@Modifying
	@Query("DELETE Cinema c where c.id=?1")
	void deleteById(Integer id);
	
	@Query("SELECT c.seatingCapacity FROM Cinema c where c.id = ?1 and c.isAvailable='Y'")
	Integer checkAvailableSeatsByCinema(Integer id);
}

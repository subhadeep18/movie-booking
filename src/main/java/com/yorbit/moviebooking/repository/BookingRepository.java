package com.yorbit.moviebooking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yorbit.moviebooking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	Page<Booking> findAll(Pageable pageable);
}

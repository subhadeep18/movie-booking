package com.yorbit.moviebooking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yorbit.moviebooking.model.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long>{
	Page<Cinema> findAll(Pageable pageable);
}

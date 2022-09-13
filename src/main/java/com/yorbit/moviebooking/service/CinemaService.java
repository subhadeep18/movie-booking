package com.yorbit.moviebooking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yorbit.moviebooking.model.Cinema;

public interface CinemaService {
	Page<Cinema> getAllCinemas(Pageable paging);

    Cinema getCinemaById(Integer id);

    Cinema saveCinema(Cinema newCinema);

    Cinema updateCinema(Cinema updatedCinema, Integer id);

    void deleteCinemaById(Integer id);
    
    Integer checkAvailableSeatsByCinema(Integer id);
}

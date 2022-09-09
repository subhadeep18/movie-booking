package com.yorbit.moviebooking.service.impl;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yorbit.moviebooking.exception.CinemaNotFoundException;
import com.yorbit.moviebooking.model.Cinema;
import com.yorbit.moviebooking.repository.CinemaRepository;
import com.yorbit.moviebooking.service.CinemaService;

@Service
@Transactional
@PropertySource("classpath:message.properties")
public class CinemaServiceImpl implements CinemaService{

	@Autowired
	private CinemaRepository cinemaRepository;
	
	private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);
	
	@Value("${cinemanotfound.message}")
	String errorMsg;
	
	@Override
	public Page<Cinema> getAllCinemas(Pageable paging) {
		return cinemaRepository.findAll(paging);
	}

	@Override
	public Cinema getCinemaById(Long id) {
		return cinemaRepository.getReferenceById(id);
	}

	@Override
	public Cinema saveCinema(Cinema newCinema) {
		return cinemaRepository.save(newCinema);
	}

	@Override
	public Cinema updateCinema(Cinema updatedCinema, Long id) {
		if(cinemaRepository.existsById(id)) {
			return cinemaRepository.save(updatedCinema);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new CinemaNotFoundException(errorMsg + id);
		}
	}

	@Override
	public void deleteCinemaById(Long id) {
		if(cinemaRepository.existsById(id)) {
			cinemaRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new CinemaNotFoundException(errorMsg + id);
		}
	}

}

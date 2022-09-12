package com.yorbit.moviebooking.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorbit.moviebooking.exception.CinemaNotFoundException;
import com.yorbit.moviebooking.model.Cinema;
import com.yorbit.moviebooking.repository.CinemaRepository;
import com.yorbit.moviebooking.service.CinemaService;

@Service
@PropertySource("classpath:message.properties")
public class CinemaServiceImpl implements CinemaService{

	@Autowired
	private CinemaRepository cinemaRepository;
	
	private static final Logger LOGGER = Logger.getLogger(CinemaServiceImpl.class);
	
	@Value("${cinemanotfound.message}")
	String errorMsg;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cinema> getAllCinemas(Pageable paging) {
		return cinemaRepository.findAll(paging);
	}

	@Override
	@Transactional(readOnly = true)
	public Cinema getCinemaById(Long id) {
		return cinemaRepository.getReferenceById(id);
	}

	@Override
	@Transactional
	public Cinema saveCinema(Cinema newCinema) {
		return cinemaRepository.save(newCinema);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
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
	@Transactional
	public void deleteCinemaById(Long id) {
		if(cinemaRepository.existsById(id)) {
			cinemaRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new CinemaNotFoundException(errorMsg + id);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Integer checkAvailableSeatsByCinema(Long id) {
		return cinemaRepository.checkAvailableSeatsByCinema(id);
	}

}

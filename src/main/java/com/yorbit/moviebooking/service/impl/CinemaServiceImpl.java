package com.yorbit.moviebooking.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorbit.moviebooking.exception.CinemaNotFoundException;
import com.yorbit.moviebooking.exception.DbException;
import com.yorbit.moviebooking.model.Cinema;
import com.yorbit.moviebooking.repository.CinemaRepository;
import com.yorbit.moviebooking.service.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService{

	@Autowired
	private CinemaRepository cinemaRepository;
	
	private static final Logger LOGGER = Logger.getLogger(CinemaServiceImpl.class);
	
	@Value("${globalexception.message}")
	String globalErrorMsg;
	
	@Value("${cinemanotfound.message}")
	String errorMsg;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cinema> getAllCinemas(Pageable paging) {
		return cinemaRepository.findAll(paging);
	}

	@Override
	@Transactional(readOnly = true)
	public Cinema getCinemaById(Integer id) {
		return cinemaRepository.getReferenceById(id);
	}

	@Override
	@Transactional
	public Cinema saveCinema(Cinema newCinema) {
		Integer result = cinemaRepository.saveCinema(newCinema.getId(), newCinema.getBookingDate(), newCinema.getBookingTime(),
				newCinema.getIsAvailable(), newCinema.getName(), newCinema.getSeatingCapacity(), newCinema.getScreen().getId());
		if(result == 1) {
			return newCinema;
		}
		else {
			throw new DbException(globalErrorMsg);
		}
	}

	@Override
	@Transactional(rollbackFor = DbException.class)
	public Cinema updateCinema(Cinema updatedCinema, Integer id) {
		if(cinemaRepository.existsById(id)) {
			Integer result = cinemaRepository.updateCinema(updatedCinema.getSeatingCapacity(),
					updatedCinema.getIsAvailable(), updatedCinema.getId());
			if(result == 1) {
				return updatedCinema;
			}
			else {
				throw new DbException(globalErrorMsg);
			}
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new CinemaNotFoundException(errorMsg + id);
		}
	}

	@Override
	@Transactional
	public void deleteCinemaById(Integer id) {
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
	public Integer checkAvailableSeatsByCinema(Integer id) {
		return cinemaRepository.checkAvailableSeatsByCinema(id);
	}

}

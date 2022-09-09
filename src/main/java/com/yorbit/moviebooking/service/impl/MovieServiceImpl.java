package com.yorbit.moviebooking.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.yorbit.moviebooking.exception.MovieNotFoundException;
import com.yorbit.moviebooking.model.Movie;
import com.yorbit.moviebooking.repository.MovieRepository;
import com.yorbit.moviebooking.service.MovieService;

@Service
@Transactional
@PropertySource("classpath:message.properties")
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);
	
	@Value("${movienotfound.message}")
	String errorMsg;
	
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie getMovieById(Long id) {
		return movieRepository.getReferenceById(id);
	}

	@Override
	public Movie saveMovie(Movie newMovie) {
		return movieRepository.save(newMovie);
	}

	@Override
	public Movie updateMovie(Movie updatedMovie, Long id) {
		if(movieRepository.existsById(id)) {
			return movieRepository.save(updatedMovie);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new MovieNotFoundException(errorMsg + id);
		}
	}

	@Override
	public void deleteMovieById(Long id) {
		if(movieRepository.existsById(id)) {
			movieRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new MovieNotFoundException(errorMsg + id);
		}
	}

}

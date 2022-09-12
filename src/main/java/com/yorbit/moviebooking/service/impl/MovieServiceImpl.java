package com.yorbit.moviebooking.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorbit.moviebooking.exception.MovieNotFoundException;
import com.yorbit.moviebooking.model.Movie;
import com.yorbit.moviebooking.repository.MovieRepository;
import com.yorbit.moviebooking.service.MovieService;

@Service
@PropertySource("classpath:message.properties")
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	private static final Logger LOGGER = Logger.getLogger(MovieServiceImpl.class);
	
	@Value("${movienotfound.message}")
	String errorMsg;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Movie> getAllMovies(Pageable paging) {
		return movieRepository.findAll(paging);
	}

	@Override
	@Transactional(readOnly = true)
	public Movie getMovieById(Long id) {
		return movieRepository.getReferenceById(id);
	}

	@Override
	@Transactional
	public Movie saveMovie(Movie newMovie) {
		return movieRepository.save(newMovie);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
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
	@Transactional
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

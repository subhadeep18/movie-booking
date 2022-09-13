package com.yorbit.moviebooking.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorbit.moviebooking.exception.DbException;
import com.yorbit.moviebooking.exception.MovieNotFoundException;
import com.yorbit.moviebooking.model.Movie;
import com.yorbit.moviebooking.repository.MovieRepository;
import com.yorbit.moviebooking.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	private static final Logger LOGGER = Logger.getLogger(MovieServiceImpl.class);
	
	@Value("${globalexception.message}")
	String globalErrorMsg;
	
	@Value("${movienotfound.message}")
	String errorMsg;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Movie> getAllMovies(Pageable paging) {
		return movieRepository.findAll(paging);
	}

	@Override
	@Transactional(readOnly = true)
	public Movie getMovieById(Integer id) {
		return movieRepository.getReferenceById(id);
	}

	@Override
	@Transactional
	public Movie saveMovie(Movie newMovie) {
		Integer result = movieRepository.saveMovie(newMovie.getId(), newMovie.getReleaseDate(),
				newMovie.getShowCycle(), newMovie.getTitle(), newMovie.getScreen().getId());
		if(result == 1) {
			return newMovie;
		}
		else {
			throw new DbException(globalErrorMsg);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Movie updateMovie(Movie updatedMovie, Integer id) {
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
	public void deleteMovieById(Integer id) {
		if(movieRepository.existsById(id)) {
			movieRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new MovieNotFoundException(errorMsg + id);
		}
	}

}

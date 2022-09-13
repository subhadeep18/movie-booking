package com.yorbit.moviebooking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yorbit.moviebooking.model.Movie;

public interface MovieService {
	Page<Movie> getAllMovies(Pageable paging);

    Movie getMovieById(Integer id);

    Movie saveMovie(Movie newMovie);

    Movie updateMovie(Movie updatedMovie, Integer id);

    void deleteMovieById(Integer id);
}

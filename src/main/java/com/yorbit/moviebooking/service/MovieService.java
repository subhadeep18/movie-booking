package com.yorbit.moviebooking.service;

import java.util.List;

import com.yorbit.moviebooking.model.Movie;

public interface MovieService {
	List<Movie> getAllMovies();

    Movie getMovieById(Long id);

    Movie saveMovie(Movie newMovie);

    Movie updateMovie(Movie updatedMovie, Long id);

    void deleteMovieById(Long id);
}

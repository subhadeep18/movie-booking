package com.yorbit.moviebooking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yorbit.moviebooking.model.Movie;
import com.yorbit.moviebooking.service.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	private static final Logger LOGGER = Logger.getLogger(MovieController.class);


    @GetMapping("/movies")
    public ResponseEntity<Map<String, Object>> getAllMovies(
		@RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "3") int size) {
    	LOGGER.info("Retrieving all movies.");
        List<Movie> movies = new ArrayList<>();
    	Pageable paging = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<Movie> pageMovies = movieService.getAllMovies(paging);
        
        movies = pageMovies.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("movies", movies);
        response.put("currentPage", pageMovies.getNumber());
        response.put("totalItems", pageMovies.getTotalElements());
        response.put("totalPages", pageMovies.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/movies/{movie_id}")
    public ResponseEntity<?> getMovieById(@PathVariable Integer movie_id) {
    	LOGGER.info("Retrieving movie by id: " + movie_id);
        Movie movie = movieService.getMovieById(movie_id);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }
    
    /* For adding data */
    @PostMapping("/movies")
    public ResponseEntity<?> saveScreen(@RequestBody Movie movie) {
    	LOGGER.info("Saving a new movie.");
    	Movie savedMovie = movieService.saveMovie(movie);
    	LOGGER.info("Movie saved successfully with id:"+ savedMovie.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @DeleteMapping("/movies/{movie_id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Integer movie_id) {
    	LOGGER.info("Cancelling the movie with id:" + movie_id);
        movieService.deleteMovieById(movie_id);
        LOGGER.info("Movie cancelled successfully for id:" + movie_id);
        return ResponseEntity.noContent().build();
    }
}

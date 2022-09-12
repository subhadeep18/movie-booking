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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yorbit.moviebooking.model.Cinema;
import com.yorbit.moviebooking.service.CinemaService;

@RestController
public class CinemaController {
	
	@Autowired
	private CinemaService cinemaService;
	
	private static final Logger LOGGER = Logger.getLogger(CinemaController.class);

    @GetMapping("/cinemas")
    public ResponseEntity<Map<String, Object>> getAllCinema(
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
    	LOGGER.info("Retrieving all cinemas.");
    	List<Cinema> cinemas = new ArrayList<>();
    	Pageable paging = PageRequest.of(page, size);
        Page<Cinema> pageCinemas = cinemaService.getAllCinemas(paging);
        
        cinemas = pageCinemas.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("cinemas", cinemas);
        response.put("currentPage", pageCinemas.getNumber());
        response.put("totalItems", pageCinemas.getTotalElements());
        response.put("totalPages", pageCinemas.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/checkSeats/{id}")
    public ResponseEntity<?> checkAvailableSeatsByCinema(@PathVariable Long id) {
    	LOGGER.info("Retriving seat availability for the cinema with id: " + id);
    	Integer seats = cinemaService.checkAvailableSeatsByCinema(id);
    	Map<String, Object> response = new HashMap<>();
        response.put("cinemaId", id);
        response.put("seats", seats);
        response.put("availability", "Y");
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/cinemas/{id}")
    public ResponseEntity<?> getCinemaById(@PathVariable Long id) {
    	LOGGER.info("Retrieving movie by id: " + id);
        Cinema cinema = cinemaService.getCinemaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cinema);
    }
    
    /* For adding data */
    @PostMapping("/cinemas")
    public ResponseEntity<?> saveScreen(@RequestBody Cinema cinema) {
    	LOGGER.info("Saving a new cinema.");
    	Cinema savedCinema = cinemaService.saveCinema(cinema);
    	LOGGER.info("Cinema saved successfully with id:"+ savedCinema.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCinema);
    }
}

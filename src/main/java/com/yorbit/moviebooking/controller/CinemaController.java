package com.yorbit.moviebooking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/cinemas")
    public ResponseEntity<Map<String, Object>> getAllCinema(
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
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
    
    /* For adding data */
    @PostMapping("/cinemas")
    public ResponseEntity<?> saveScreen(@RequestBody Cinema cinema) {
    	Cinema savedCinema = cinemaService.saveCinema(cinema);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCinema);
    }
}

package com.yorbit.moviebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yorbit.moviebooking.model.Screen;
import com.yorbit.moviebooking.service.ScreenService;

@RestController
public class ScreenController {
	
	@Autowired
	private ScreenService screenService;

    @GetMapping("/screens")
    public List<Screen> getAllScreens() {
        return screenService.getAllScreens();
    }
    
    /* For adding data */
    @PostMapping("/screens")
    public ResponseEntity<?> saveScreen(@RequestBody Screen screen) {
        Screen savedScreen = screenService.saveScreen(screen);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScreen);
    }
}

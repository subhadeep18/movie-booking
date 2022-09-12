package com.yorbit.moviebooking.controller;

import java.util.List;

import org.jboss.logging.Logger;
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
	
	private static final Logger LOGGER = Logger.getLogger(ScreenController.class);

    @GetMapping("/screens")
    public List<Screen> getAllScreens() {
    	LOGGER.info("Retrieving all screens.");
        return screenService.getAllScreens();
    }
    
    /* For adding data */
    @PostMapping("/screens")
    public ResponseEntity<?> saveScreen(@RequestBody Screen screen) {
    	LOGGER.info("Saving a new screen.");
        Screen savedScreen = screenService.saveScreen(screen);
        LOGGER.info("Screen saved successfully with id:"+ savedScreen.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScreen);
    }
}

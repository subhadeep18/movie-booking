package com.yorbit.moviebooking.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yorbit.moviebooking.model.User;
import com.yorbit.moviebooking.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @GetMapping("/users")
    public List<User> getAllUsers() {
    	LOGGER.info("Retrieving all users.");
        return userService.getAllUsers();
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
    	LOGGER.info("Retrieving user by id: " + id);
        User movie = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }
    
    /* For adding data */
    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
    	LOGGER.info("Saving a new user.");
        User savedUser = userService.saveUser(user);
        LOGGER.info("User saved successfully with id:"+ savedUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}

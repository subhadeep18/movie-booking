package com.yorbit.moviebooking.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.yorbit.moviebooking.exception.UserNotFoundException;
import com.yorbit.moviebooking.model.User;
import com.yorbit.moviebooking.repository.UserRepository;
import com.yorbit.moviebooking.service.UserService;

@Service
@Transactional
@PropertySource("classpath:message.properties")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);
	
	@Value("${usernotfound.message}")
	String errorMsg;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.getReferenceById(id);
	}

	@Override
	public User saveUser(User newUser) {
		return userRepository.save(newUser);
	}

	@Override
	public User updateUser(User updatedUser, Long id) {
		if(userRepository.existsById(id)) {
			return userRepository.save(updatedUser);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new UserNotFoundException(errorMsg + id);
		}
	}

	@Override
	public void deleteUserById(Long id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new UserNotFoundException(errorMsg + id);
		}
	}

}

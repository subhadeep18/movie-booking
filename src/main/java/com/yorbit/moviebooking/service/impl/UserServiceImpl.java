package com.yorbit.moviebooking.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorbit.moviebooking.exception.DbException;
import com.yorbit.moviebooking.exception.UserNotFoundException;
import com.yorbit.moviebooking.model.User;
import com.yorbit.moviebooking.repository.UserRepository;
import com.yorbit.moviebooking.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	
	@Value("${globalexception.message}")
	String globalErrorMsg;
	
	@Value("${usernotfound.message}")
	String errorMsg;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserById(Integer id) {
		return userRepository.getReferenceById(id);
	}

	@Override
	@Transactional
	public User saveUser(User newUser) {
		Integer result = userRepository.saveUser(newUser.getId(), newUser.getUserName(), newUser.getPassword());
		if(result == 1) {
			return newUser;
		}
		else {
			throw new DbException(globalErrorMsg);
		}
	}

	@Override
	@Transactional
	public User updateUser(User updatedUser, Integer id) {
		if(userRepository.existsById(id)) {
			return userRepository.save(updatedUser);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new UserNotFoundException(errorMsg + id);
		}
	}

	@Override
	@Transactional
	public void deleteUserById(Integer id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new UserNotFoundException(errorMsg + id);
		}
	}

}

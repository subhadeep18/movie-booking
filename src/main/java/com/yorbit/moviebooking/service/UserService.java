package com.yorbit.moviebooking.service;

import java.util.List;

import com.yorbit.moviebooking.model.User;

public interface UserService {
	List<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User newUser);

    User updateUser(User updatedUser, Long id);

    void deleteUserById(Long id);
}

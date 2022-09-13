package com.yorbit.moviebooking.service;

import java.util.List;

import com.yorbit.moviebooking.model.User;

public interface UserService {
	List<User> getAllUsers();

    User getUserById(Integer id);

    User saveUser(User newUser);

    User updateUser(User updatedUser, Integer id);

    void deleteUserById(Integer id);
}

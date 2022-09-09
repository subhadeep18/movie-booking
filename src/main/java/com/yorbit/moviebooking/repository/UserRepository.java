package com.yorbit.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorbit.moviebooking.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}

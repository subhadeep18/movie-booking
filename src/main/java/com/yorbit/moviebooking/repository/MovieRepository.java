package com.yorbit.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorbit.moviebooking.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}

package com.yorbit.moviebooking.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yorbit.moviebooking.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
	Page<Movie> findAll(Pageable pageable);
	
	@Modifying
	@Query(value="INSERT into movie(movie_id, releaseDate, showCycle, title, screen_id) VALUES (?1, ?2, ?3, ?4, ?5);",
			nativeQuery=true)
	Integer saveMovie(Integer id, LocalDate releaseDate, String showCycle, String title, Integer screen_id);
	
	@Modifying
	@Query("DELETE Movie m where m.id=?1")
	void deleteById(Integer id);
}

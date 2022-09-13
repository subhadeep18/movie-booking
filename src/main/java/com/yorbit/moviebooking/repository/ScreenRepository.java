package com.yorbit.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yorbit.moviebooking.model.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Integer>{
	@Modifying
	@Query(value="INSERT into screen(screen_id, type) VALUES (?1, ?2);",
			nativeQuery=true)
	Integer saveScreen(Integer id, String type);

	@Modifying
	@Query("DELETE Screen b where b.id=?1")
	void deleteById(Integer id);
}

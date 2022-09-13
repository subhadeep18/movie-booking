package com.yorbit.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yorbit.moviebooking.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Modifying
	@Query(value="INSERT into user(user_id, password, user_name) VALUES (?1, ?2, ?3);",
			nativeQuery=true)
	Integer saveUser(Integer id, String userName, String password);
	
	@Modifying
	@Query("DELETE User u where u.id=?1")
	void deleteById(Integer id);
}

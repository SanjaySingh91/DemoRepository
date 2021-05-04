package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	@Query("select u from User u where u.email=:email")
	public User getUserByUserName(@Param("email") String email);
	
	@Query("select u from User u where u.status=:status and u.username LIKE CONCAT('%',:name,'%')")
	public List<User> getAllUserByUserName(@Param("status") String status, @Param("name") String name);

}

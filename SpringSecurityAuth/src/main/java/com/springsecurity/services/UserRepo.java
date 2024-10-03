package com.springsecurity.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springsecurity.model.Users;


@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

	@Query("SELECT u FROM Users u WHERE u.username = :username")
	Users findByUsername(String username);

}

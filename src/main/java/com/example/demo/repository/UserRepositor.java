package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Admin;

public interface UserRepositor extends JpaRepository<Admin, Long> {

	@Query(value="select * from admin where username =:username and password =:password ", nativeQuery=true)
	Admin getElementByUsernameAndPassowrd(@Param("username") String username,@Param("password") String password);
	
}

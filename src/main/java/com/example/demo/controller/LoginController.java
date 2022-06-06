package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Admin;
import com.example.demo.model.Makeup;
import com.example.demo.repository.MakeupRepository;
import com.example.demo.repository.UserRepositor;

@CrossOrigin(origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v7/")
public class LoginController {
	@Autowired
	private UserRepositor userRepository;
	
	@GetMapping("/login")
	public List<Admin> getAllAdmin(){
		return userRepository.findAll();
	}
	
	// hadhi statique juste njarbou baha
	@GetMapping("/get-one-user")
	public Admin getOneAdmin(){
		return this.userRepository.getElementByUsernameAndPassowrd("ryhemm", "ryhem");
	}
	
	@PostMapping("/get-by-username") 
	public Admin getElementByUsernameAndPassowrd(@RequestBody Admin admin) {
		// System.out.println("----------------------------------------------");
		// System.out.println(admin);
		return this.userRepository.getElementByUsernameAndPassowrd(admin.getUserName(), admin.getPassWord());
	}
	
	
}

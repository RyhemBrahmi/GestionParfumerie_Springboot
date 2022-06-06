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
import com.example.demo.model.ParfumsFemme;
import com.example.demo.repository.ParfumsFemmeRepository;

@CrossOrigin(origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v/")
public class ParfumsFemmeController {

	@Autowired
	private ParfumsFemmeRepository parfumsFemmeRepository;

	//get all parfumsFemme
	
		@GetMapping("/parfumsFemme")
		public List<ParfumsFemme> getAllParfumsFemme(){
		return parfumsFemmeRepository.findAll();
		}
		
		//create ParfumsFemme rest api
		
		@PostMapping("/parfumsFemme")
		public ParfumsFemme createParfumsFemme(@RequestBody ParfumsFemme parfumsFemme) {
			return parfumsFemmeRepository.save(parfumsFemme);
		}
		//get ParfumsFemme by id rest api
		
		@GetMapping("/parfumsFemme/{id}")
		public ResponseEntity<ParfumsFemme> getParfumsFemmeById(@PathVariable Long id) {
			ParfumsFemme parfumsFemme = parfumsFemmeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Parfums not exist with id :" + id));
			return ResponseEntity.ok(parfumsFemme);
		}
		
		//update parfums res api
		@PutMapping("/parfumsFemme/{id}")
		public ResponseEntity<ParfumsFemme> updatedParfumsFemme(@PathVariable Long id,@RequestBody ParfumsFemme parfumsFemmeDetails){
			ParfumsFemme parfumsFemme = parfumsFemmeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("parfumsFemme not exist with id :" + id));
			parfumsFemme.setNomProduit(parfumsFemmeDetails.getNomProduit());
			parfumsFemme.setDescription(parfumsFemmeDetails.getDescription());
			parfumsFemme.setPrix(parfumsFemmeDetails.getPrix());
			parfumsFemme.setQuantite(parfumsFemmeDetails.getQuantite());
			parfumsFemme.setImage(parfumsFemmeDetails.getImage());
			
			ParfumsFemme updatedParfumsFemme = parfumsFemmeRepository.save(parfumsFemme);
			return ResponseEntity.ok(updatedParfumsFemme);
		}
		//delete parfumsFemme rest api 
		@DeleteMapping("/parfumsFemme/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteParfumsHomme(@PathVariable Long id){
			ParfumsFemme parfumsFemme = parfumsFemmeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Parfums not exist with id :" + id));
			parfumsFemmeRepository.delete(parfumsFemme);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
		
}

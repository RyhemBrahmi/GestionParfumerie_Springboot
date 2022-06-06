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
import com.example.demo.model.ParfumsHomme;
import com.example.demo.repository.ParfumsHommeRepository;


@CrossOrigin(origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v2/")
public class ParfumsHommeController {
	@Autowired
	private ParfumsHommeRepository parfumsHommeRepository;

	//get all parfumsHomme
	
		@GetMapping("/parfumsHomme")
		public List<ParfumsHomme> getAllParfums(){
		return parfumsHommeRepository.findAll();
		}
		
		//create parfumsHomme rest api
		//create parfums rest api
		@PostMapping("/parfumsHomme")
		public ParfumsHomme createParfumsHomme(@RequestBody ParfumsHomme parfumsHomme) {
			return parfumsHommeRepository.save(parfumsHomme);
		}
		//get parfums by id rest api
		
		@GetMapping("/parfumsHomme/{id}")
		public ResponseEntity<ParfumsHomme> getParfumsById(@PathVariable Long id) {
			ParfumsHomme parfumsHomme = parfumsHommeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Parfums not exist with id :" + id));
			return ResponseEntity.ok(parfumsHomme);
		}
		
		//update parfums res api
		@PutMapping("/parfumsHomme/{id}")
		public ResponseEntity<ParfumsHomme> updatedParfumsHomme(@PathVariable Long id,@RequestBody ParfumsHomme parfumsHommeDetails){
			ParfumsHomme parfumsHomme = parfumsHommeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("ParfumsHomme not exist with id :" + id));
			parfumsHomme.setNomProduit(parfumsHommeDetails.getNomProduit());
			parfumsHomme.setDescription(parfumsHommeDetails.getDescription());
			parfumsHomme.setPrix(parfumsHommeDetails.getPrix());
			parfumsHomme.setQuantite(parfumsHommeDetails.getQuantite());
			parfumsHomme.setImage(parfumsHommeDetails.getImage());
			
			ParfumsHomme updatedParfumsHomme = parfumsHommeRepository.save(parfumsHomme);
			return ResponseEntity.ok(updatedParfumsHomme);
		}
		//delete parfumshomme rest api 
		@DeleteMapping("/parfumsHomme/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteParfumsHomme(@PathVariable Long id){
			ParfumsHomme parfumsHomme = parfumsHommeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Parfums not exist with id :" + id));
			parfumsHommeRepository.delete(parfumsHomme);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
		
}

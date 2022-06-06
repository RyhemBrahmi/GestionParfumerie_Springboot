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
import com.example.demo.model.BeautyHair;
import com.example.demo.repository.BeautyHairRepository;

@CrossOrigin(origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v4/")
public class BeautyHairController {

	@Autowired
	private BeautyHairRepository beautyHairRepository;

	//get all beautyHair
	
		@GetMapping("/beautyHair")
		public List<BeautyHair> getAllBeautyHair(){
		return beautyHairRepository.findAll();
		}
		
		//create makeup rest api
		
		@PostMapping("/beautyHair")
		public BeautyHair createBeautyHair(@RequestBody BeautyHair beautyHair) {
			return beautyHairRepository.save(beautyHair);
		}
		//get beautyHair by id rest api
		
		@GetMapping("/beautyHair/{id}")
		public ResponseEntity<BeautyHair> getBeautyHairById(@PathVariable Long id) {
			BeautyHair beautyHair = beautyHairRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("beautyHair not exist with id :" + id));
			return ResponseEntity.ok(beautyHair);
		}
		
		//update beautyHair res api
		@PutMapping("/beautyHair/{id}")
		public ResponseEntity<BeautyHair> updatedBeautyHair(@PathVariable Long id,@RequestBody BeautyHair beautyHairDetails){
			BeautyHair beautyHair = beautyHairRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("BeautyHair not exist with id :" + id));
			beautyHair.setNomProduit(beautyHairDetails.getNomProduit());
			beautyHair.setDescription(beautyHairDetails.getDescription());
			beautyHair.setPrix(beautyHairDetails.getPrix());
			beautyHair.setQuantite(beautyHairDetails.getQuantite());
			beautyHair.setImage(beautyHairDetails.getImage());
			
			BeautyHair updatedBeautyHair = beautyHairRepository.save(beautyHair);
			return ResponseEntity.ok(updatedBeautyHair);
		}
		//delete beautyHair rest api 
		@DeleteMapping("/beautyHair/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteBeautyHair(@PathVariable Long id){
			BeautyHair beautyHair = beautyHairRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("beautyHair not exist with id :" + id));
			beautyHairRepository.delete(beautyHair);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	
}

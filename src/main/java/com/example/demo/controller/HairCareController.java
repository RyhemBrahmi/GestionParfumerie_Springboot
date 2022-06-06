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
import com.example.demo.model.HairCare;
import com.example.demo.model.Makeup;
import com.example.demo.repository.HairCareRepository;
import com.example.demo.repository.MakeupRepository;

@CrossOrigin(origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v6/")

public class HairCareController {

	@Autowired
	private HairCareRepository hairCareRepository;

	//get all hairCare
	
		@GetMapping("/hairCare")
		public List<HairCare> getAllHairCare(){
		return hairCareRepository.findAll();
		}
		
		//create hairCare rest api
		
		@PostMapping("/hairCare")
		public HairCare createHairCare(@RequestBody HairCare hairCare) {
			return hairCareRepository.save(hairCare);
		}
		//get hairCare by id rest api
		
		@GetMapping("/hairCare/{id}")
		public ResponseEntity<HairCare> getHairCareById(@PathVariable Long id) {
			HairCare hairCare = hairCareRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("hairCare not exist with id :" + id));
			return ResponseEntity.ok(hairCare);
		}
		
		//update hairCare res api
		@PutMapping("/hairCare/{id}")
		public ResponseEntity<HairCare> updatedHairCare(@PathVariable Long id,@RequestBody HairCare HairCareDetails){
			HairCare hairCare = hairCareRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("hairCare not exist with id :" + id));
			hairCare.setNomProduit(HairCareDetails.getNomProduit());
			hairCare.setDescription(HairCareDetails.getDescription());
			hairCare.setPrix(HairCareDetails.getPrix());
			hairCare.setQuantite(HairCareDetails.getQuantite());
			hairCare.setImage(HairCareDetails.getImage());
			
			HairCare updatedHairCare = hairCareRepository.save(hairCare);
			return ResponseEntity.ok(updatedHairCare);
		}
		//delete hairCare rest api 
		@DeleteMapping("/hairCare/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteHairCare(@PathVariable Long id){
			HairCare hairCare = hairCareRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("hairCare not exist with id :" + id));
			hairCareRepository.delete(hairCare);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
		
}

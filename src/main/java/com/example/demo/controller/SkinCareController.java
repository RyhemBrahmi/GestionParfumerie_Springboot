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
import com.example.demo.model.SkinCare;
import com.example.demo.repository.SkinCareRepository;

@CrossOrigin(origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v5/")
public class SkinCareController {

	@Autowired
	private SkinCareRepository skinCareRepository;

	//get all skinCare
	
		@GetMapping("/skinCare")
		public List<SkinCare> getAllSkinCare(){
		return skinCareRepository.findAll();
		}
		
		//create skinCare rest api
		
		@PostMapping("/skinCare")
		public SkinCare createSkinCare(@RequestBody SkinCare skinCare) {
			return skinCareRepository.save(skinCare);
		}
		//get skinCare by id rest api
		
		@GetMapping("/skinCare/{id}")
		public ResponseEntity<SkinCare> getSkinCareById(@PathVariable Long id) {
			SkinCare skinCare = skinCareRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("skinCare not exist with id :" + id));
			return ResponseEntity.ok(skinCare);
		}
		
		//update skinCare res api
		@PutMapping("/skinCare/{id}")
		public ResponseEntity<SkinCare> updatedSkinCare(@PathVariable Long id,@RequestBody SkinCare skinCareDetails){
			SkinCare skinCare = skinCareRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("makeup not exist with id :" + id));
			skinCare.setNomProduit(skinCareDetails.getNomProduit());
			skinCare.setDescription(skinCareDetails.getDescription());
			skinCare.setPrix(skinCareDetails.getPrix());
			skinCare.setQuantite(skinCareDetails.getQuantite());
			skinCare.setImage(skinCareDetails.getImage());
			
			SkinCare updatedSkinCare = skinCareRepository.save(skinCare);
			return ResponseEntity.ok(updatedSkinCare);
		}
		//delete skinCare rest api 
		@DeleteMapping("/skinCare/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteSkinCare(@PathVariable Long id){
			SkinCare skinCare = skinCareRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("skinCare not exist with id :" + id));
			skinCareRepository.delete(skinCare);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
		
}

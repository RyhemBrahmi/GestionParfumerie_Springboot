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
import com.example.demo.model.Makeup;
import com.example.demo.repository.MakeupRepository;

@CrossOrigin(origins = "http://localhost:3000/" )
@RestController
@RequestMapping("/api/v3/")
public class MakeupController {
	@Autowired
	private MakeupRepository makeupRepository;

	//get all makeup
	
		@GetMapping("/makeup")
		public List<Makeup> getAllMakeup(){
		return makeupRepository.findAll();
		}
		
		//create makeup rest api
		
		@PostMapping("/makeup")
		public Makeup createMakeup(@RequestBody Makeup makeup) {
			return makeupRepository.save(makeup);
		}
		//get makeup by id rest api
		
		@GetMapping("/makeup/{id}")
		public ResponseEntity<Makeup> getMakeupById(@PathVariable Long id) {
			Makeup makeup = makeupRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("makeup not exist with id :" + id));
			return ResponseEntity.ok(makeup);
		}
		
		//update makeup res api
		@PutMapping("/makeup/{id}")
		public ResponseEntity<Makeup> updatedMakeup(@PathVariable Long id,@RequestBody Makeup makeupDetails){
			Makeup makeup = makeupRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("makeup not exist with id :" + id));
			makeup.setNomProduit(makeupDetails.getNomProduit());
			makeup.setDescription(makeupDetails.getDescription());
			makeup.setPrix(makeupDetails.getPrix());
			makeup.setQuantite(makeupDetails.getQuantite());
			makeup.setImage(makeupDetails.getImage());
			
			Makeup updatedMakeup = makeupRepository.save(makeup);
			return ResponseEntity.ok(updatedMakeup);
		}
		//delete makeup rest api 
		@DeleteMapping("/makeup/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteMakeup(@PathVariable Long id){
			Makeup makeup = makeupRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("makeup not exist with id :" + id));
			makeupRepository.delete(makeup);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
		
}

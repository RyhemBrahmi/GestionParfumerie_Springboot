package com.example.demo.controller;

import java.util.Base64;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.AbstractJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.DatatypeConverter;

@RestController
@RequestMapping("/api/upload")
public class UploadController {
 
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("image")
	public String uplaodImage(@RequestBody String baseImage_src) {

		
	//	String base64Image = "iVBORw0KGgoAAAANSUhEUgAAAAgAAAAIAQMAAAD+wSzIAAAABlBMVEX///+/v7+jQ3Y5AAAADklEQVQI12P4AIX8EAgALgAD/aNpbtEAAAAASUVORK5CYII";
	//	String base64Image2 = "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";

		System.out.println("Trying to save the image");
		
		

		System.out.println(baseImage_src);

		
		//String replacedString = baseImage_src.replace("=", "");
		//String replacedStringfinal = replacedString.replace("", "");

		String replacedString = baseImage_src.replace("%2F", "/");
		//String replacedString2 = replacedString.replace("%2", "/");
		String replacedString2 = replacedString.replace("=", "");
		//String replacedStringfinal = replacedString2.replace("%3D", "=");
		
		/*
		
		String replacedString = baseImage_src.replace("%2F", "/");
		String replacedString2 = replacedString.replace("%2", "/");
		String replacedStringfinal = replacedString2.replace("%3D", "=");
		*/
		
		 
		// save image and return image src
		String result = "";
		String baseUplaodUrl = "D:\\Parfumerie-frontend\\image_upload\\";
		String returnedUplaodUrl = "http://localhost:7777/";
		
		        try {
		            //This will decode the String which is encoded by using Base64 class
		            byte[] imageByte = Base64.getDecoder().decode(replacedString2);
		              //         
		            String uuid = UUID.randomUUID().toString();
		            
		            String path = baseUplaodUrl + uuid +".jpg";
		            Path file = Paths.get(path);
		            Files.write(file, imageByte);
		             
		            // Creating a direct link for the image
		            result = returnedUplaodUrl + uuid +".jpg";
		            
		            System.out.println(result);
		        }
		        catch(Exception e) {
		           System.out.println(e);
		           result = "Fail";
		        }
		         
		            
		        return result;
		        
		        
		         
		}
	
	
	
	
	
	
	
	
/*	public String Upload(@RequestBody String baseImage) {
		// System.out.println(baseImage);
		
		String base64String = baseImage;
		
		/*
		 * Create new image on the server using the baseImage(base64 encoded image)
		 * Return link to the saved image
		 * */
		
		// Create image  on server
		  /* String[] strings = base64String.split(",");
		   
		   System.out.println(base64String);
		   // System.out.prin 
	         
	        //convert base64 string to binary data
		   
	        byte[] data = DatatypeConverter.parseBase64Binary(base64String);
	        
	        // Image name
	        String uuid = UUID.randomUUID().toString();
	        
	        String path = "D:\\Parfumerie-frontend\\image_upload\\" + uuid + "." + "jpg";
	        
	        File file = new File(path);
	        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
	            outputStream.write(data);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
		
		return "D:\\Parfumerie-frontend\\image_upload\\image.png";
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("helloworld")
	public String Hello() {
		System.out.println("hello");
		return "hello" ;
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
}

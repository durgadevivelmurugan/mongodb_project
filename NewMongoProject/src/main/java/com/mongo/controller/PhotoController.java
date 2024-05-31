package com.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongo.entity.Photo;
import com.mongo.service.PhotoService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/photo")
public class PhotoController {

	@Autowired
	PhotoService photoService;
	 
	
	@PostMapping("/post")
	//if we are working with multipart file the we have to configure it(application.properties)
	//app.properties==>size,etc..
	public String addPhoto(@RequestParam("image") MultipartFile image) throws Exception {
		
		//to save image and with its original name 
		
		String id=photoService.addPhoto(image.getOriginalFilename(),image);
		return id;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Resource> downloadPhoto(@PathVariable String id){
		Photo photo=photoService.getPhoto(id);
		Resource resource= 
				(Resource) new ByteArrayResource(photo.getPhoto().getData());
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" +photo.getTitle() +"\"" )
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
	
	
	
	
}

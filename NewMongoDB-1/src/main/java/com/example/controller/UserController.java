package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repository;
@GetMapping("/get")
public List<User>getStudents(){
	return service.getStudents();
}
@PostMapping("/save")
public User saveStudent(@RequestBody User user) {
	return service.saveStudent(user);
}

@PutMapping("/update/{id}")
public User updateStudent(@PathVariable String id,@RequestBody User user) {
	return service.updateStudent(id, user);
}

@DeleteMapping("/delete/{id}")
public User deleteStudent(@PathVariable String id) {
	return service.deleteStudent(id);
}
@GetMapping("/getpageone")
public List<User> getpageone(){
	
	Pageable paging =PageRequest.of(0, 3,Sort.by("name").ascending());
	Page<User>page=repository.findAll(paging);
	
	
	return page.getContent();
	}
}
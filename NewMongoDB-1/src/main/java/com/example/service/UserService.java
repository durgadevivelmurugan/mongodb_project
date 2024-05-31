package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {
	public List<User> getStudents();
	public User saveStudent(User user);
	public User updateStudent(String id, User user);
	public User deleteStudent(String id);
}

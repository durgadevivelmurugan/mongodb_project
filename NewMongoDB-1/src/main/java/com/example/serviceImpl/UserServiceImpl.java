package com.example.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	@Override
	public List<User> getStudents() {
		return repository.findAll();
	}

	@Override
	public User saveStudent(User user) {
		return repository.save(user);
	}

	public User deleteStudent(String id) {
		User user=repository.findById(id).get();
		repository.delete(user);
		return user;
	}

	@Override
	@Transactional
	public User updateStudent(String id, User user) {
		User updateuser=repository.findById(id).get();
		updateuser.setName(user.getName());
		updateuser.setAge(user.getAge());
		 repository.save(updateuser);
		 return updateuser;
	}

}

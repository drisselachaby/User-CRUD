package com.uesr.userCrud.service;

import com.uesr.userCrud.entity.User;
import com.uesr.userCrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public List<User> getUsers(){
		return userRepo.findAll();
	}

	public Optional<User> getUser(Long id){
		return userRepo.findById(id);
	}
public User saveUser(User user){
		return userRepo.saveAndFlush(user);
}
public boolean existsById(Long id) {
    return userRepo.existsById(id);
}
	public String deleteUser(Long id) {
		if (userRepo.findById(id).isPresent()) {
			userRepo.deleteById(id);
			return "Employee *"+id+ "* deleted successfully";
		}
		return "No such employee *" +id+ "* in the database";
	}
}

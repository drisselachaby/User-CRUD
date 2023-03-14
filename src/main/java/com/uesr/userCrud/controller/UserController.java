package com.uesr.userCrud.controller;

import com.uesr.userCrud.entity.User;
import com.uesr.userCrud.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/users")
	public List<User> getAllUsers(){

		return userService.getUsers();
	}
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Long id){
		return userService.getUser(id).
				orElseThrow(
						() -> new EntityNotFoundException("Request User not found")
			   	           );
	}
	@PostMapping("user")
	public User addUser(@RequestBody User user) {
	    return userService.saveUser(user);
	}
	@PutMapping("/user/{id}")
	public ResponseEntity<?> modifier(@RequestBody User user, @PathVariable Long id){
		if(userService.existsById(id))
		{
			User user1 = userService.getUser(id).orElseThrow(() -> new EntityNotFoundException("Request User not found"));
			user1.setFirstName(user.getFirstName());
			user1.setLastName(user.getLastName());
			user1.setEmail(user.getEmail());
			user1.setTelephone(user.getTelephone());
			userService.saveUser(user);
			return ResponseEntity.ok().body(user1);
		}
		else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", id + " User not Found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
	@DeleteMapping("/user/{id}")
	public String deleteUserById(@PathVariable("id") Long id) {
		return userService.deleteUser(id);
	}
}

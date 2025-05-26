package com.marpe.cht.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.marpe.cht.entities.User;
import com.marpe.cht.repositories.UserRepository;
import com.marpe.cht.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;
	private final UserRepository userRepository;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public UserController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Optional<User>> validateUser(@RequestBody ObjectNode JSONObject) {
		String username = JSONObject.get("username").asText();
        String password = JSONObject.get("password").asText();
  
		Optional<User> optUser = userRepository.findByUsername(username);
		if(optUser.isEmpty()) {
			  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		  
		boolean valid = encoder.matches(password, optUser.get().getPassword());
		if(valid) {
			HttpStatus status = HttpStatus.OK;
			return ResponseEntity.status(status).body(optUser);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	 
}

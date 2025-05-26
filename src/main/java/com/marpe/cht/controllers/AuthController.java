package com.marpe.cht.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marpe.cht.entities.User;
import com.marpe.cht.entities.dtos.AuthRequest;
import com.marpe.cht.entities.dtos.AuthResponse;
import com.marpe.cht.jwt.JwtService;
import com.marpe.cht.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, UserService userService,
    		JwtService jwtService) {
        this.authManager = authManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody @Valid AuthRequest request) {
		log.info("Starting registration process.");
	    User user = userService.registerUser(request);
	
	    String token = jwtService.generateToken(user.getUsername(), user.getRole());
	
	    return ResponseEntity.ok(Map.of("token", token));
	  }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		log.info("Starting authentication process.");
		
		Authentication auth = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
		Authentication authenticated = authManager.authenticate(auth);

		SecurityContextHolder.getContext().setAuthentication(authenticated);
		
        User user = (User) authenticated.getPrincipal();
        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(new AuthResponse(token, user.getUsername(), user.getRole()));
    }

}

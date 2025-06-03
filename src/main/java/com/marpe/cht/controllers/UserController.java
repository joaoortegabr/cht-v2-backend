package com.marpe.cht.controllers;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marpe.cht.entities.User;
import com.marpe.cht.entities.dtos.UserPasswordRequest;
import com.marpe.cht.entities.dtos.UserRequest;
import com.marpe.cht.entities.dtos.UserResponse;
import com.marpe.cht.entities.mappers.UserMapper;
import com.marpe.cht.services.UserService;
import com.marpe.cht.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	UserMapper mapper = Mappers.getMapper(UserMapper.class);
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<Page<UserResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll Users");
		Page<User> userPage = userService.findAll(paginationRequest);
		Page<UserResponse> userResponsePage = userPage.map(mapper::toUserResponse);
	    return ResponseEntity.ok(userResponsePage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
		log.info("Receiving request to findById an User with param: id={}", id);
		UserResponse user = mapper.toUserResponse(userService.findById(id));
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping(value = "/password/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody UserPasswordRequest request) {
		log.info("Receiving request to change password of User with params: id={} and user={}", id, request);
		String result = userService.changePassword(id, request);
		return ResponseEntity.ok().body(result);
	}	
	
	@PutMapping(value = "/role/{id}")
	public ResponseEntity<UserResponse> changeRole(@PathVariable Long id, @RequestBody UserRequest request) {
		log.info("Receiving request to change role of User with params: id={} and user={}", id, request);
		User user = mapper.toUser(request);
		User updatedUser = userService.changeRole(id, user);
		UserResponse userResponse = mapper.toUserResponse(updatedUser);
		return ResponseEntity.ok().body(userResponse);
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("Receiving request to delete an User with param: id={}", id);
		String msg = userService.delete(id);
		return ResponseEntity.ok(msg);
	}
	 
}

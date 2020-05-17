package com.pri.microservices.rest.user;

import java.net.URI;
import java.util.List;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	UserDaoService dao;

	@GetMapping(path = "/users")
	public List<User> retrieveUsers() {
		return dao.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		
		User returnedUser = dao.findOne(id);
		
		if(returnedUser==null) {
 
			throw new UserNotFoundException("id-"+id);
		}
		
		return returnedUser;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser( @RequestBody User user) {

		User savedUser = dao.saveUser(user);

		URI location=ServletUriComponentsBuilder.
		fromCurrentRequest().path("/{id}"). //get the current request URI and append pathparam
		buildAndExpand(savedUser.getId()).toUri(); //setting the pathparama value
		 
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User returnedUser = dao.deleteOne(id);
				
		if(returnedUser==null)
			throw new UserNotFoundException("id-"+id);
	}

}

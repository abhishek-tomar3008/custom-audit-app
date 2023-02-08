package com.jlw.user;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<User> createUserHandler(@RequestBody User user){
		User userDb=userService.createUser(user);
		return new ResponseEntity<User>(userDb,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/user")
	public ResponseEntity<User>updateUser(@RequestBody User user) throws UserPrincipalNotFoundException{
		User userDb=userService.UpdateUser(user);
		return new ResponseEntity<User>(userDb,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String>deleteUser(@PathVariable Integer id) throws UserPrincipalNotFoundException{
		String msg=userService.deleteUser(id);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}

}

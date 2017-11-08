package com.stackroute.activitystream.userservice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.backend.dao.UserDAO;
import com.stackroute.activitystream.backend.model.UserRegistration;

@RestController
public class UserController {

	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("/")
	public String sample()
	{
		return "hello";
	}
	
	@GetMapping(value = "/user/{email}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRegistration> getUser(@PathVariable("email") String email) {
		UserRegistration singleUser = userDAO.findByEmail(email);
		if (singleUser == null) {
			return new ResponseEntity<UserRegistration>(HttpStatus.NOT_FOUND);
		}
		else
		{
		return new ResponseEntity<UserRegistration>(singleUser, HttpStatus.OK);
		}
	}
	
	
	@PostMapping(value = "/user/")
	public ResponseEntity<String> createUser(@RequestBody UserRegistration userModel) {
		if (userDAO.findByEmail(userModel.getUserEmail()) == null) {
			userDAO.saveUser(userModel);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value = "/user")
	public ResponseEntity<List<UserRegistration>> listAllUsers() {
		List<UserRegistration> users = userDAO.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserRegistration>>(HttpStatus.NO_CONTENT);} else {
			return new ResponseEntity<List<UserRegistration>>(users, HttpStatus.OK);
		}
	}
	
	
	@PutMapping(value = "/user/{email}")
	public ResponseEntity<UserRegistration> updateUser(@PathVariable("email") String email, @RequestBody UserRegistration user) {
		UserRegistration currentuser = userDAO.findByEmail(email);
		if (currentuser == null) {
			return new ResponseEntity<UserRegistration>(HttpStatus.NOT_FOUND);
		} else {
			userDAO.updateUser(user);
			return new ResponseEntity<UserRegistration>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/user/{emailId}")
	public ResponseEntity<UserRegistration> deleteUser(@PathVariable("emailId") String emailId) {
		UserRegistration user = userDAO.findByEmail(emailId);
		if (user == null) {
			return new ResponseEntity<UserRegistration>(HttpStatus.NOT_FOUND);
		}
		userDAO.deleteUserByEmail(emailId);
		return new ResponseEntity<UserRegistration>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserRegistration> loginUser(@RequestBody UserRegistration userLoggingIn,HttpSession session)
	{
		UserRegistration user=userDAO.findByEmail(userLoggingIn.getUserEmail());
		if(userDAO.userLogin(userLoggingIn.getUserEmail(), userLoggingIn.getUserPassword()))
		{
			session.setAttribute("userLoggedInName", userLoggingIn.getUserName());
			session.setAttribute("userLoggedInEmail", userLoggingIn.getUserEmail());
			return new ResponseEntity<UserRegistration>(userLoggingIn,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<UserRegistration>(user,HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutUser(HttpSession session)
	{
		String userLoggedIn=session.getAttribute("userLoggedInName").toString();
		if(userLoggedIn!=null)
		{
			session.invalidate();
			session.setMaxInactiveInterval(0);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}

}

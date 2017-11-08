package com.stackroute.activitystream.backend.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.activitystream.backend.model.UserRegistration;


public interface UserDAO {
	public UserRegistration findByEmail(String email);

	public boolean saveUser(UserRegistration user);

	public boolean updateUser(UserRegistration user);

	public boolean deleteUserByEmail(String emailId);

	public List<UserRegistration> findAllUsers();
	
	public boolean userLogin(String emailId,String password);
	
	public boolean userLogout(String emailId);

}

/*package com.stackroute.activitystream.userservice;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.backend.dao.UserDAO;
import com.stackroute.activitystream.backend.model.UserRegistration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserServiceApplicationTests {

	@Autowired
	public UserDAO userDAO;
	public static UserRegistration userRegistration;

	@BeforeClass
	public static void ObjectCreator() {
		userRegistration = new UserRegistration();
	}

	@Test
	public void updateUser() {
		userRegistration = userDAO.findByEmail("milaga@gmail.com");
		System.out.println(userRegistration.getUserFullName());
		userRegistration.setUserAddress("Bangalore city");
		boolean updatingUser = userDAO.updateUser(userRegistration);
		assertEquals(true, updatingUser);
	}

	@Test
	public void testGetUserByEmail() {


		UserRegistration userByName = userDAO.findByEmail("milaga@gmail.com");
		assertNotNull(userByName);
		System.out.println("User Email ID :" + userByName.getUserFullName());
	}

	@Test
	public void testDeleteUser() {
		assertTrue(userDAO.deleteUserByEmail("milaga@gmail.com"));
	}

	@Test
	public void saveUser() {
		userRegistration.setUserEmail("milaga@gmail.com");
		userRegistration.setUserName(" Malu");
		userRegistration.setUserAddress("savam");
		userRegistration.setUserContact(1234567890);
		userRegistration.setUserFullName("im malu");
		userRegistration.setUserPassword("malu");
		System.out.println(userRegistration.getUserName());
		assertTrue(userDAO.saveUser(userRegistration));
	}
	
	@Test
	public void listOfUser()
	{
		List<UserRegistration> userList=userDAO.findAllUsers();
		assertNotNull(userList);
	}


}
*/
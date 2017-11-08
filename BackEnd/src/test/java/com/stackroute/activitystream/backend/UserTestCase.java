package com.stackroute.activitystream.backend;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.backend.dao.CircleDAO;
import com.stackroute.activitystream.backend.dao.MessageDAO;
import com.stackroute.activitystream.backend.dao.UserCircleDAO;
import com.stackroute.activitystream.backend.dao.UserDAO;
import com.stackroute.activitystream.backend.model.Circle;
import com.stackroute.activitystream.backend.model.Message;
import com.stackroute.activitystream.backend.model.UserCircle;
import com.stackroute.activitystream.backend.model.UserRegistration;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes=BackEndApplication.class)
@EnableAspectJAutoProxy
public class UserTestCase {

	@Autowired
	public UserDAO userDAO;
	@Autowired
	public CircleDAO circleDAO;
	@Autowired
	public UserCircleDAO userCircleDAO;
	@Autowired
	public MessageDAO messageDAO;

	public static Circle circle;
	public static UserRegistration userRegistration;
	public static UserCircle userCircle;
	public static Message message;

	@BeforeClass
	public static void ObjectCreator() {
		userRegistration = new UserRegistration();
		circle = new Circle();
		userCircle = new UserCircle();
		message=new Message();
	}

	@Test
	public void updateUser() {
		userRegistration = userDAO.findByEmail("kodha@gmail.com");
		System.out.println(userRegistration.getUserFullName());
		userRegistration.setUserAddress("Bangalore city");
		boolean updatingUser = userDAO.updateUser(userRegistration);
		assertEquals(true, updatingUser);
	}

	@Test
	public void testGetUserByEmail() {

		UserRegistration userByName = userDAO.findByEmail("poda@gmail.com");
		assertNotNull(userByName);
		System.out.println("User Email ID :" + userByName.getUserFullName());
	}

	@Test
	public void testDeleteUser() {
		assertTrue(userDAO.deleteUserByEmail("milaga@gmail.com"));
	}

	@Test
	public void saveUser() {
		userRegistration.setUserEmail("gamma@gmail.com");
		userRegistration.setUserName(" gamma");
		userRegistration.setUserAddress("pakki");
		userRegistration.setUserContact(100100100);
		userRegistration.setUserFullName("im poda");
		userRegistration.setUserPassword("pikkali");
		System.out.println(userRegistration.getUserName());
		assertTrue(userDAO.saveUser(userRegistration));
	}

	@Test
	public void listOfUser() {
		List<UserRegistration> userList = userDAO.findAllUsers();
		assertNotNull(userList);
	}

	
}

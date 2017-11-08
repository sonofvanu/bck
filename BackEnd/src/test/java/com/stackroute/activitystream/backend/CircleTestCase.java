package com.stackroute.activitystream.backend;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.backend.dao.CircleDAO;
import com.stackroute.activitystream.backend.dao.MessageDAO;
import com.stackroute.activitystream.backend.dao.UserCircleDAO;
import com.stackroute.activitystream.backend.dao.UserDAO;
import com.stackroute.activitystream.backend.model.Circle;
import com.stackroute.activitystream.backend.model.Message;
import com.stackroute.activitystream.backend.model.UserCircle;
import com.stackroute.activitystream.backend.model.UserRegistration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CircleTestCase {

	@Autowired
	public CircleDAO circleDAO;
	@Autowired
	public UserCircleDAO userCircleDAO;
	

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
	public void createCircle() {
		circle.setCircleName("koi");
		circle.setCreatedOn();
		assertTrue(circleDAO.createCircle(circle));

	}

	@Test
	public void updateCircle() {
		circle = circleDAO.findCircleById(1);
		circle.setCreatedOn();
		assertTrue(circleDAO.updateCircle(circle));
	}

	@Test
	public void allCircles() {
		List<Circle> listOfCircles = circleDAO.listOfAllCircles();
		assertNotNull(listOfCircles);
	}

	@Test
	public void deleteCircle() {
		assertTrue(circleDAO.deleteCircle(1));
	}

	@Test
	public void addUserTocircle() {
		circle = circleDAO.findCircleById(3);
		userCircle.setCircleId(circle.getCircleId());
		userCircle.setCircleName(circle.getCircleName());
		userCircle.setJoinedOn();
		userCircle.setUserId("milaga@gmail.com");

		assertTrue(userCircleDAO.addUserToCircle(userCircle));

	}

	@Test
	public void removeUserFromCircle() {
		assertTrue(userCircleDAO.removeUserFromCircle("milaga@gmail.com", 3));
	}

	@Test
	public void singleListUsers() {
		assertNotNull(userCircleDAO.listOfCirclesOfAuser("milaga@gmail.com"));
	}

	@Test
	public void singleUserCircleList() {
		assertNotNull(userCircleDAO.listOfUsersOfACircle("hoi"));
	}
	
	
}

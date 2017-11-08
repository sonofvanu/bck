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
public class MessageTestCase {

	@Autowired
	public UserDAO userDAO;
	@Autowired
	public CircleDAO circleDAO;
	@Autowired
	public MessageDAO messageDAO;

	public static Circle circle;
	public static UserRegistration userRegistration;
	public static UserCircle userCircle;
	public static Message message;

	@BeforeClass
	public static void ObjectCreator() {
		message=new Message();
	}

	
	@Test
	public void sendUsermessage()
	{
		message.setMessageActual("hello how are you-------im fine");
		message.setMessageSentOn();
		message.setMessageSentAt();
		userRegistration=userDAO.findByEmail("milaga@gmail.com");
		message.setSenderId(userRegistration.getUserEmail());
		userRegistration=userDAO.findByEmail("poda@gmail.com");
		message.setReceiverId(userRegistration.getUserEmail());
		assertTrue(messageDAO.sendMessage(message));
	}
	
	@Test
	public void sendCircleMessage()
	{
		circle=circleDAO.findCircleById(2);
		message.setCircleId(circle.getCircleId());
		message.setMessageActual("hello pakkis");
		message.setMessageSentOn();
		message.setMessageSentAt();
		userRegistration=userDAO.findByEmail("poda@gmail.com");
		message.setSenderId(userRegistration.getUserEmail());
		messageDAO.sendMessage(message);
	}

}

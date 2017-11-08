package com.stackroute.activitystream.userservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.activitystream.backend.dao.UserDAO;
import com.stackroute.activitystream.userservice.controller.UserController;

public class UserServiceApplicationMockMVCTest {

	
	private MockMvc mockMvc;
	@Mock
	private UserDAO userDAO;
	@InjectMocks
	private UserController userController;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	public void getAllUsersTest() throws Exception
	{
		mockMvc.perform(get("/user/"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}
	
	
	
	
	

}

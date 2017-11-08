package com.stackroute.activitystream.messageservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.backend.dao.MessageDAO;
import com.stackroute.activitystream.backend.model.Message;

@RestController
public class MessageController {
	
	@Autowired
	MessageDAO messageDAO;
	
	@PostMapping(value={"/messageCircle"},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sendMessageToCircle(@RequestBody Message message)
	{
		if(messageDAO.sendMessage(message))
		{
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}
	
	
	@PostMapping(value={"/messageUser"},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sendMessageToUser(@RequestBody Message message)
	{
		if(messageDAO.sendMessage(message))
		{
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value={"/messageCircle/{circleName}/{senderId}"},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Message>> messageSentToACircle(@PathVariable("circleName") String circleName,@PathVariable("senderId") String senderId)
	{
		
		List<Message> messageSentToCircle=messageDAO.allMessageToACircle(circleName, senderId);
		if(messageSentToCircle!=null)
		{
			return new ResponseEntity<List<Message>>(messageSentToCircle,HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<List<Message>>(HttpStatus.CONFLICT);
		}
	}
	
	
	@GetMapping(value={"/messageUser/{senderId}/{receiverId}"},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Message>> messageSentToAUser(@PathVariable("senderId") String senderId,@PathVariable("receiverId") String receiverId)
	{
		List<Message> messageSentToCircle=messageDAO.allMessageToAUser(senderId, receiverId);
		if(messageSentToCircle!=null)
		{
			return new ResponseEntity<List<Message>>(messageSentToCircle,HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<List<Message>>(HttpStatus.CONFLICT);
		}
	}

}

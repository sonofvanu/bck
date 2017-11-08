package com.stackroute.activitystream.backend.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.activitystream.backend.model.Message;



public interface MessageDAO {
	
	public boolean sendMessage(Message message);
	
	public boolean removeMessageToAUser(int messageId);
	
	public boolean removeMessageFromACircle(int messageId);
	
	public List<Message> allMessageToAUser(String senderId,String receiverId);
	
	public List<Message> allMessagesSentByAUser(String senderId);
	
	public List<Message> allMessageToACircle(String circleName,String senderId);
	
	public List<Message> allMessageOfACircle(String circleName);

}

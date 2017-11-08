package com.stackroute.activitystream.backend.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.activitystream.backend.model.Circle;


public interface CircleDAO {
	public boolean createCircle(Circle circle);
	
	public boolean updateCircle(Circle circle);
	
	public boolean deleteCircle(int circleId);
	
	public Circle findCircleById(int circleId);
	
	public List<Circle> listOfAllCircles();
	

}

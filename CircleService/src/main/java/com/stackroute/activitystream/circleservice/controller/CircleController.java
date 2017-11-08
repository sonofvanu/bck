package com.stackroute.activitystream.circleservice.controller;

import java.util.List;

import javax.print.attribute.standard.MediaPrintableArea;

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
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.backend.dao.CircleDAO;
import com.stackroute.activitystream.backend.model.Circle;

@RestController
public class CircleController {
	
	@Autowired
	CircleDAO circleDAO;
	
	@PostMapping(value="/circle", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createCircle(@RequestBody Circle circle)
	{
		if(circleDAO.createCircle(circle))
		{
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		{
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
	}
	
	
	@GetMapping(value="/circle", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Circle>> getAllCircle()
	{
		List<Circle> allCircles=circleDAO.listOfAllCircles();
		if(allCircles.isEmpty())
		{
			return new ResponseEntity<List<Circle>>(HttpStatus.EXPECTATION_FAILED);
		}
		else
		{
			return new ResponseEntity<List<Circle>>(allCircles,HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value="/circle/{circleId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Circle> getCircle(@PathVariable("circleId") int circleId)
	{
		Circle singleCircle=circleDAO.findCircleById(circleId);
		if(singleCircle==null){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		else
		{
			return new ResponseEntity<>(singleCircle,HttpStatus.FOUND);
		}
	}
	
	@DeleteMapping(value="/circle/{circleId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCircle(@PathVariable("circleId") int circleId)
	{
		if(circleDAO.deleteCircle(circleId))
		{
			return new ResponseEntity<String>(HttpStatus.GONE);
		}
		else
		{
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping(value="/circle/{circleId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCircle(@PathVariable("circleId") int circleId,@RequestBody Circle circle)
	{
		Circle updateCircle=circleDAO.findCircleById(circleId);
		if(updateCircle==null){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		else
		{
			circleDAO.updateCircle(circle);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
	}
	

}

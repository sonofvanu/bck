package com.stackroute.activitystream.backend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.backend.dao.CircleDAO;
import com.stackroute.activitystream.backend.model.Circle;

@Repository(value = "circleDAO")
@Transactional
@EnableTransactionManagement
public class CircleDAOImpl implements CircleDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean createCircle(Circle circle) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(circle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateCircle(Circle circle) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(circle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteCircle(int circleId) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(findCircleById(circleId));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Circle findCircleById(int circleId) {
		// TODO Auto-generated method stub

		Circle circle = new Circle();
		circle = sessionFactory.getCurrentSession().get(Circle.class, circleId);
		return circle;

	}

	@Override
	public List<Circle> listOfAllCircles() {
		// TODO Auto-generated method stub
		List<Circle> listOfAllCircles = sessionFactory.getCurrentSession().createQuery("FROM Circle").list();
		return listOfAllCircles;
	}

}

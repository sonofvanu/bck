package com.stackroute.activitystream.backend.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.backend.dao.UserCircleDAO;
import com.stackroute.activitystream.backend.model.UserCircle;


@Repository(value = "userCircleDAO")
@Transactional
@EnableTransactionManagement
public class UserCircleDAOImpl implements UserCircleDAO {
	
	public static final Logger logger=LoggerFactory.getLogger(UserCircleDAOImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addUserToCircle(UserCircle userCircle) {
		// TODO Auto-generated method stub
		try
		{
		sessionFactory.getCurrentSession().save(userCircle);
		logger.debug("User "+userCircle.getUserId()+" has been saved to the circle "+userCircle.getCircleName());
			return true;
		}
		catch(Exception e)
		{
			logger.error("User "+userCircle.getUserId()+" cannot be saved to the circle "+userCircle.getCircleName());
			return false;
		}
		
	}

	@Override
	public boolean removeUserFromCircle(String userId, int circleId) {
		// TODO Auto-generated method stub
		try
		{
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UserCircle.class);
			criteria.add(Restrictions.eq("userId", userId)).add(Restrictions.eq("circleId", circleId));
			List<UserCircle> matchedUserCircle=criteria.list();
			if(matchedUserCircle.size()>=1)
			{
				UserCircle userCircle=matchedUserCircle.get(0);
				sessionFactory.getCurrentSession().delete(userCircle);
				logger.debug("User "+userCircle.getUserId()+" has been removed from the circle "+userCircle.getCircleName());
				return true;
			}
			else
			{
				logger.error("User cannot be removed from the circle "+circleId);
				return false;
			}
		}
		catch(Exception e)
		{
			logger.error("error occured in user circle module in user removal from a circle");
			return false;
		}
	}

	@Override
	public List<UserCircle> listOfCirclesOfAuser(String userId) {
		// TODO Auto-generated method stub
		try
		{
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UserCircle.class);
			criteria.add(Restrictions.eq("userId",userId ));
			List<UserCircle> listOfCircleOfUser=criteria.list();
			if(listOfCircleOfUser.size()>=1)
			{
				logger.debug("The list of circle of a user has been generated for user "+userId);
				return listOfCircleOfUser;
			}
			else
			{
				logger.error("The user is not in any circle");
				return null;
			}
		}
		catch(Exception e)
		{
			logger.error("Error occurred in user circle module in list of circle of a user");
			return null;	
		}
		
	}

	@Override
	public List<UserCircle> listOfUsersOfACircle(String circleName) {
		// TODO Auto-generated method stub
		try
		{
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UserCircle.class);
			criteria.add(Restrictions.eq("circleName", circleName));
			List<UserCircle> listOfUsersOfCircle=criteria.list();
			if(listOfUsersOfCircle.size()>=1)
			{
				logger.debug("the list of users of a circle has been generated for the circle "+circleName);
				return listOfUsersOfCircle;
			}
			else
			{
				logger.error("there are no users joined this circle");
				return null;
			}
		}
		catch(Exception e)
		{
			logger.error("error in user circvle module for list of users in a circle");
			return null;
		}
	}

}

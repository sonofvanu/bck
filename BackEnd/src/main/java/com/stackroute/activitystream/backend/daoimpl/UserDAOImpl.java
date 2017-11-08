package com.stackroute.activitystream.backend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.backend.dao.UserDAO;
import com.stackroute.activitystream.backend.model.UserRegistration;



@Repository(value = "userDAO")
@Transactional
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO {

public static final Logger logger=LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired 
	SessionFactory sessionFactory;

	@Override
	public UserRegistration findByEmail(String email) {
		// TODO Auto-generated method stub
		UserRegistration userRegistration = new UserRegistration();
		userRegistration = (UserRegistration) sessionFactory.getCurrentSession().get(UserRegistration.class, email);
		if(userRegistration==null)
		{
			logger.error("cannot find user based on the email "+email);
			return null;
		}else
		{
			logger.debug("Found the user "+userRegistration.getUserName()+" and have been returned");
		return userRegistration;
		}
	}

	@Override
	public boolean saveUser(UserRegistration userRegistration) {
		// TODO Auto-generated method stub
		try {
			
			sessionFactory.getCurrentSession().save(userRegistration);
			logger.debug("user successfully registered "+userRegistration.getUserFullName());
			return true;
		} catch (Exception e) {
			logger.error("cannot save the user "+userRegistration.getUserFullName());
			return false;
		}
	}

	@Override
	public boolean updateUser(UserRegistration userRegistration) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(userRegistration);
			logger.debug("user info updated for "+userRegistration.getUserFullName());
			return true;
		} catch (Exception e) {
			logger.error("user info cannot be updated for "+userRegistration.getUserFullName());
			return false;
		}
	}

	@Override
	public boolean deleteUserByEmail(String emailId) {
		// TODO Auto-generated method stub
		try {
			
			sessionFactory.getCurrentSession().delete(findByEmail(emailId));
			logger.debug("user deleted with email id "+emailId);
			return true;
		} catch (Exception e) {
			logger.error("user cannot be deleted with email id ");
			return false;
		}
	}

	@Override
	public List<UserRegistration> findAllUsers() {
		// TODO Auto-generated method stub
		List<UserRegistration> showuser = sessionFactory.getCurrentSession().createQuery("FROM UserRegistration").list();
		if(showuser.size()>=1)
		{
			logger.debug("user list provided");
		return showuser;
		}
		else
		{
			logger.error("user list cannot be obtaineds");
			return null;
		}
	}

	@Override
	public boolean userLogin(String emailId, String password) {
		// TODO Auto-generated method stub
		try
		{
			UserRegistration userLoggingIn=findByEmail(emailId);
			if(userLoggingIn.getUserPassword().equals(password))
			{
				userLoggingIn.setOnline(true);
				sessionFactory.getCurrentSession().update(userLoggingIn);
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(Exception e)
		{
			return false;
		}
	}

	
	@Override
	public boolean userLogout(String emailId) {
		// TODO Auto-generated method stub
		
		UserRegistration userLoggingOut=findByEmail(emailId);
		try {
			userLoggingOut.setOnline(false);
			sessionFactory.getCurrentSession().update(userLoggingOut);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	
}

package com.stackroute.activitystream.usercircleservice;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.stackroute.activitystream.backend.daoimpl.CircleDAOImpl;
import com.stackroute.activitystream.backend.daoimpl.UserCircleDAOImpl;

@SpringBootApplication(scanBasePackages="com.stackroute.activitystream")
@Import(UserCircleDAOImpl.class)
public class UserCircleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCircleServiceApplication.class, args);
	}
	
	@Bean(value = "sessionFactory")
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory) {
	    return hibernateEntityManagerFactory.getSessionFactory();
	}
}

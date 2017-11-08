package com.stackroute.activitystream.circleservice;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.stackroute.activitystream.backend.daoimpl.CircleDAOImpl;

@SpringBootApplication(scanBasePackages="com.stackroute.activitystream")
@Import(CircleDAOImpl.class)
public class CircleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircleServiceApplication.class, args);
	}
	
	
	@Bean(value = "sessionFactory")
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory) {
	    return hibernateEntityManagerFactory.getSessionFactory();
	}
}

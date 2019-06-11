package com.example.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Department {
	Logger logger = (Logger) LoggerFactory.getLogger(Department.class);
	
	@Autowired
	@Qualifier("dev")
	private IEmployee empl;
	
	public IEmployee getEmpl() {
		return empl;
	}
	
	@PostConstruct
	public void postConstruct() {
		logger.debug("postConstruct");
	}
	
	@PreDestroy
	public void preDestroy() {
		logger.info("preDestroy");
	}
}

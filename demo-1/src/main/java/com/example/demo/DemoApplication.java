package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		
		  ConfigurableApplicationContext ctx =
		  SpringApplication.run(DemoApplication.class, args); Department department =
		  ctx.getBean(Department.class);
		  
		  IEmployee empl = department.getEmpl(); Id id2 = empl.getId(); empl.getType();
		  
		  IEmployee empl2 = department.getEmpl(); Id id = empl2.getId();
		  
		  LOGGER.info("Department name: " + department.getName());
		 
	}

}

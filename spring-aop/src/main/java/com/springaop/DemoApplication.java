package com.springaop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {
	private static Logger log = LoggerFactory.getLogger(DemoApplication.class);

	@GetMapping(value="/")
	public String getRoot(){
		return "Hello";
	}
	
	public static void main(String[] args) {
		 ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
		 Person person = ctx.getBean(Person.class);
		 person.setCanDrive(true);
		 person.setName("Jacko");

		 log.info("Car: " + person.getCar().getName());
	}

}

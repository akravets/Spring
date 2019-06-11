package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Demo1Application {
	Logger LOGGER = (Logger) LoggerFactory.getLogger(Demo1Application.class);
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Demo1Application.class, args);
		Department bean = ctx.getBean(Department.class);
		IEmployee empl = bean.getEmpl();
		Id id2 = empl.getId();
		empl.getType();
		
		IEmployee empl2 = bean.getEmpl();
		Id id = empl2.getId();
	}

}

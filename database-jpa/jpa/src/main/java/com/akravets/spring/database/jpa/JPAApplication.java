package com.akravets.spring.database.jpa;

import java.util.Date;

import com.akravets.spring.database.jpa.model.Person;
import com.akravets.spring.database.jpa.repository.PersonRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JPAApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(JPAApplication.class);

	@Autowired
	PersonRepository dao;

	public static void main(String[] args) {
		SpringApplication.run(JPAApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Person p = dao.findById(1003);
		logger.info(p.toString());

		dao.insert(new Person("Bobzas", "PAsq", new Date()));
		dao.insert(new Person("Bobzas", "PAsq", new Date()));
		dao.insert(new Person("Bobzas", "PAsq", new Date()));

		dao.update(new Person(1001,"Alexis", "NJ", new Date()));
		
	}
}

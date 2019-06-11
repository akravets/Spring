package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Person {
	@Autowired
	private Id id;
	
	public Person(Id id) {
		super();
		this.id = id;
	}
}
	

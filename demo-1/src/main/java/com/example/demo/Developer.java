package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("dev")
public class Developer extends AbstractEmployee {
	@Autowired
	private Id id;

	public Developer() {
		super(Type.SALARY);
	}

	@Override
	public Id getId() {
		return id;
	}
}

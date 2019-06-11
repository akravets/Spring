package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("manager")
public class Manager extends AbstractEmployee {
	
	protected Manager() {
		super(Type.SALARY);
	}

	@Autowired
	private Id id;
	
	
	@Override
	public Id getId() {
		return id;
	}
}

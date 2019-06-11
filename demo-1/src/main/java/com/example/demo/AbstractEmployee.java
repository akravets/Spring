package com.example.demo;

public abstract class AbstractEmployee implements IEmployee {
	protected Type type;
	
	protected AbstractEmployee(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}

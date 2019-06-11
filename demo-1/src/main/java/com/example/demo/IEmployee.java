package com.example.demo;

public interface IEmployee {
	public static enum Type {
		CONTRACT, SALARY;
	}
	
	public Id getId();
	
	public Type getType();
}

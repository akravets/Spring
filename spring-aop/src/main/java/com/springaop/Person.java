package com.springaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person{
    private String name;
    private Car car;
    private boolean canDriver;

    public Person(){}

    public Person(String name) {
       this.name = name;
    }

    @Autowired
    public void setCar(Car car){
        this.car = car;
    }

    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

	public void setCanDrive(boolean canDriver) {
        this.canDriver = canDriver;
    }

    public boolean isCanDriver() {
        return canDriver;
    } 
}
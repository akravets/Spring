package com.springaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car{
    private String name;

    public Car(){}

    @Autowired
    @Qualifier("carName")
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
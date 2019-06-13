package com.springaop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Config{
    @Bean
    public Car getCar(){
        return new Car();
    }

    @Bean(name="carName")
    public String getCarName(){
        return "Lexus";
    }
}
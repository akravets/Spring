package com.springaop.aop;

import com.springaop.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DriveRightsAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // weaving (on which resources: classes, packages,etc. to apply this middleware 
    @Before("execution(* com.example.helloworld.Person.getCar(..))")
    public void hasRights(JoinPoint jPoint) {
        // advice: what to do during intersept
        Person person = (Person) jPoint.getTarget();
        if (!person.isCanDriver()) {
            logger.info(person.getName() + " cannot drive a car");
            return;
        }
        logger.info(person.getName() + " can drive a car");
    }
}

package com.akravets.spring.database.jpa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity // flags that this class maps to db table
@Table //(name="person") if name of class is different from db table name
public class Person {
    @Id // primary key
    @GeneratedValue // value should generated
    private int id;
    private String name;
    private String location;
    private Date birthday;

    // We need to have no-args constructor when using JPA
    public Person(){}

    public Person(int id, String name, String location, Date birthday) {
        super();
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthday = birthday;
    }

    // A constuctor for our class where Id is generated
    public Person(String name, String location, Date birthday) {
        super();
        this.name = name;
        this.location = location;
        this.birthday = birthday;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }
    
    @Override
    public String toString() {
        return String.format("\n[Person id=%s, name=%s, location=%s, birthday=%s]", id, name, location, birthday);
    }

}
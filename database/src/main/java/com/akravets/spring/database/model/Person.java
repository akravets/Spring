package com.akravets.spring.database.model;

import java.util.Date;

public class Person {
    private int id;
    private String name;
    private String location;
    private Date birthday;

    /*
        whenver we use BeanPropertyRowMapper (PersonDAO) we need to make sure our model class (Person)
        has no args constructor, otherwise during run of application we would ge exception.
    */
    public Person(){}

    public Person(int id, String name, String location, Date birthday) {
        super();
        this.id = id;
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
package com.akravets.spring.database.repository;

import java.util.List;
import java.util.Map;

import com.akravets.spring.database.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAO {
    @Autowired
    JdbcTemplate template;

    public List<Person> findAll() {
        // we can use Person class using BeanPropertyRowMapper because fields in Person
        // class
        // correspond to those in the database.

        // whenver we use BeanPropertyRowMapper we need to make sure our model class
        // (Person)
        // has no args constructor, otherwise during run of application we would ge
        // exception.
        return template.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
    }

    public List<Map<String, Object>> findByLocation(String location) {
        return template.queryForList("select * from person where location=?", new Object[]{location});
    }
}
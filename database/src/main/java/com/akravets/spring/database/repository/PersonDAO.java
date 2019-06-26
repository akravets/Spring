package com.akravets.spring.database.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.akravets.spring.database.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAO {
    @Autowired
    JdbcTemplate template;

    //We can use custom RowMapper to map fields from database to our POJO
    class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id");
            person.setName(rs.getString("name"));
            person.setLocation(rs.getString("location"));
            person.setBirthday(rs.getTimestamp("birthday"));
            return null;
        }

    }

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
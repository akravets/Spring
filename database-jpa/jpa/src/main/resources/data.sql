/* 
    Database schema file, this file will be picked up by Spring during
    running of the app and execute instructions
*/


/*
With JPA we don't need to create schema for the table, Spring Boot will automatically
generate schema for us

create table person
(
    id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    birthday TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);
*/

INSERT into person (id, name, location, birthday) VALUES(1001,'Alex', 'NJ', sysdate());
INSERT into person (id, name, location, birthday) VALUES(1002,'Bob', 'NJ', sysdate());
INSERT into person (id, name, location, birthday) VALUES(1003,'Mike', 'TX', sysdate());
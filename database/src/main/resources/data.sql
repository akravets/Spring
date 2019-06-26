/* 
    Database schema file, this file will be picked up by Spring during
    running of the app and execute instructions
*/


create table person
(
    id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    birthday TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

INSERT into person (id, name, location, birthday) VALUES(1,'Alex', 'NJ', sysdate());
INSERT into person (id, name, location, birthday) VALUES(2,'Bob', 'NJ', sysdate());
INSERT into person (id, name, location, birthday) VALUES(3,'Mike', 'TX', sysdate());
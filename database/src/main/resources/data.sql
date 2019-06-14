/* 
    Database schema file, this file will be picked up by Spring during
    running of the app and execute instructions
*/


create table person
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    birthday TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

INSERT into person (name, location, birthday) VALUES('Alex', 'NJ', sysdate());
INSERT into person (name, location, birthday) VALUES('Bob', 'NY', sysdate());
INSERT into person (name, location, birthday) VALUES('Mike', 'TX', sysdate());
INSERT into person (name, location, birthday) VALUES('Jane', 'PA', sysdate());

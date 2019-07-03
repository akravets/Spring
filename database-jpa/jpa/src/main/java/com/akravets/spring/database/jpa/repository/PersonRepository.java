package com.akravets.spring.database.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.akravets.spring.database.jpa.model.Person;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PersonRepository{
    @PersistenceContext
    EntityManager entityManager;

    public Person findById(int id){
        return entityManager.find(Person.class, id);
    }

    /**
     * Insert new record
     * @param person {@link Person} to be added
     * @return
     */
    public Person insert(Person person){
        return entityManager.merge(person);
    }

    /**
     * Update record
     * @param person {@link Person} to be updated
     * @return
     */
    public Person update(Person person){
        return entityManager.merge(person);
    }
}
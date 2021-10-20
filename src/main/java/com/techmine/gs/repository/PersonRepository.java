package com.techmine.gs.repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.techmine.gs.domain.Person;

public class PersonRepository extends AbstractRepository<Person, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonRepository() {
        super(Person.class);
    }

}

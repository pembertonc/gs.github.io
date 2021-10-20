package com.techmine.gs.repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.techmine.gs.domain.Contact;

public class ContactRepository extends AbstractRepository<Contact, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactRepository() {
        super(Contact.class);
    }

}

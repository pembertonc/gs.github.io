package com.techmine.repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.techmine.domain.Address;

public class AddressRepository extends AbstractRepository<Address, Long> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressRepository() {
        super(Address.class);
    }

}

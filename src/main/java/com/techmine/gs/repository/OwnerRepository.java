package com.techmine.gs.repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.techmine.gs.domain.Owner;

public class OwnerRepository extends AbstractRepository<Owner, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OwnerRepository() {
        super(Owner.class);
    }

}

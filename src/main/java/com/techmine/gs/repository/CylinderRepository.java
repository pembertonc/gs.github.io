package com.techmine.gs.repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.techmine.gs.domain.Cylinder;

public class CylinderRepository extends AbstractRepository<Cylinder, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CylinderRepository() {
        super(Cylinder.class);
    }

}

package com.techmine.gs.repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.techmine.gs.domain.GasType;

public class GasTypeRepository extends AbstractRepository<GasType, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GasTypeRepository() {
        super(GasType.class);
    }

}

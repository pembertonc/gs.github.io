package com.techmine.gs.repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.techmine.gs.domain.UnitOfMeasure;

public class UnitOfMeasureRepository extends AbstractRepository<UnitOfMeasure, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UnitOfMeasureRepository() {
        super(UnitOfMeasure.class);
    }

}

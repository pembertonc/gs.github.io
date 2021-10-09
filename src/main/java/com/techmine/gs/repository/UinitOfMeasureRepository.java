package com.techmine.gs.repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.techmine.gs.domain.UinitOfMeasure;

public class UinitOfMeasureRepository extends AbstractRepository<UinitOfMeasure, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UinitOfMeasureRepository() {
        super(UinitOfMeasure.class);
    }

}

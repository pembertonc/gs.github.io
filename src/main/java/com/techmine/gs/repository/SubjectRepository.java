package com.techmine.gs.repository;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import com.techmine.gs.domain.Subject;

public class SubjectRepository extends AbstractRepository<Subject, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubjectRepository() {
        super(Subject.class);
    }
}

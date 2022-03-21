package com.techmine.gs.repository;

import javax.persistence.EntityManager;
import com.techmine.gs.domain.Subject;
import javax.inject.Inject;

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

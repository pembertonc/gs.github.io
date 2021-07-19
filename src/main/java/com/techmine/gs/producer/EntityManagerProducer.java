package com.techmine.gs.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Producer for injectable EntityManager
 *
 */
@RequestScoped
public class EntityManagerProducer {

    @PersistenceContext(unitName = "GS_PU")
    private EntityManager em;

    @Produces
    public EntityManager getEntityManager() {
        return em;
    }

}

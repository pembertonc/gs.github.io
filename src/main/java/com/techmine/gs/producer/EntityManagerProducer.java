package com.techmine.gs.producer;

/*
import javax.enterprise.inject.Produces;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;*/
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Producer for injectable EntityManager
 *
 */
@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceContext(unitName = "GS_PU")
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /*  protected void closeEntityManager(@Disposes EntityManager entityManager) {
    if (entityManager.isOpen()) {
    entityManager.close();
    }
    }*/
}

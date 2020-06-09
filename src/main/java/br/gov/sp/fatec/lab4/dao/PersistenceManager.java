package br.gov.sp.fatec.lab4.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

    private static PersistenceManager manager;

    private EntityManagerFactory factory;

    private PersistenceManager(){}

    public static PersistenceManager getInstance() {
        if(manager == null){
            manager = new PersistenceManager();
        }
        return manager;
    }

    public EntityManagerFactory getEntityManagerFactory(){
        if(factory == null){
            factory = Persistence.createEntityManagerFactory("atividade2");
        }
        return factory;
    }

    public EntityManager getEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }
}

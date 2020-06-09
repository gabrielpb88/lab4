package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;

import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {

        //TODO: Faça uso da anotação @Inheritance com duas estratégias diferentes.
        EntityManager manager = PersistenceManager
                .getInstance().getEntityManager();

    }
}

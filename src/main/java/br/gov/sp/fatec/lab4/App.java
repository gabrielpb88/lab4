package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;

import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {

        //TODO: Utilize @MappedSuperclass para evitar declarar esse coluna em todas as classes.

        //TODO: Faça uso da anotação @Inheritance com duas estratégias diferentes.
        EntityManager manager = PersistenceManager
                .getInstance().getEntityManager();

    }
}

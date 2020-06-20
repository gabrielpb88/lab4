package br.gov.sp.fatec.lab4.dao;

import br.gov.sp.fatec.lab4.entities.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

public class ClienteDao {

    private EntityManager manager = PersistenceManager.getInstance().getEntityManager();

    public Cliente buscar(Long id){
        return manager.find(Cliente.class, id);
    }

    public void salvar(Cliente cliente) {
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();
    }

    public void deletar(Cliente cliente){
        manager.getTransaction().begin();
        manager.remove(cliente);
        manager.getTransaction().commit();
    }

    public void atualizar(Cliente cliente){
        manager.getTransaction().begin();
        manager.merge(cliente);
        manager.getTransaction().commit();
    }

}

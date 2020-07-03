package br.gov.sp.fatec.lab4.dao;

import br.gov.sp.fatec.lab4.entities.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class ClienteDao {

    private EntityManager manager;

    public ClienteDao() {
        this.manager = PersistenceManager.getInstance().getEntityManager();
    }

    public ClienteDao(EntityManager manager) {
        this.manager = manager;
    }

    public Cliente findById(Long id) {
        return manager.find(Cliente.class, id);
    }

    public List<Cliente> findByAttributes(Map<String, String> atributes) {
        if (atributes == null) return null;

        String queryText = "select c from Cliente c where ";
        int size = atributes.size();
        int i = 0;
        for(String s : atributes.keySet()){
            queryText += "c." + s + " like :" + s;
            i++;
            if(i < size) queryText += " and ";
        }
        TypedQuery<Cliente> typedQuery = manager.createQuery(queryText, Cliente.class);
        for(String s : atributes.keySet()){
            typedQuery.setParameter(s, atributes.get(s));
        }

        return typedQuery.getResultList();
    }

    public List<Cliente> findByAttribute(String attribute, String value){
        String queryText = "select c from Cliente c where c." + attribute + " like " + ":" + attribute;

        TypedQuery<Cliente> typedQuery = manager.createQuery(queryText, Cliente.class);
        typedQuery.setParameter(attribute, value);

        return typedQuery.getResultList();
    }

    public void save(Cliente cliente) {
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();
    }

    public void delete(Cliente cliente){
        manager.getTransaction().begin();
        manager.remove(cliente);
        manager.getTransaction().commit();
    }

    public void update(Cliente cliente){
        try {
            manager.getTransaction().begin();
            manager.merge(cliente);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Erro ao atualizar entidade");
        }
    }

    public void salvarSemCommit(Cliente cliente) {
        if (cliente.getId() == null) {
            manager.persist(cliente);
        } else {
            manager.merge(cliente);
        }

    }

}

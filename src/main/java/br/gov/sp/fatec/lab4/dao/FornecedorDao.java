package br.gov.sp.fatec.lab4.dao;

import br.gov.sp.fatec.lab4.entities.Fornecedor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class FornecedorDao {

    private EntityManager manager = PersistenceManager.getInstance().getEntityManager();

    public Fornecedor findById(Long id){
        return manager.find(Fornecedor.class, id);
    }

    public List<Fornecedor> findByAttributes(Map<String, String> atributes){
        if (atributes == null) return null;

        String queryText = "select f from Fornecedor f where ";
        int size = atributes.size();
        int i = 0;
        for(String s : atributes.keySet()){
            queryText += "f." + s + " like :" + s;
            i++;
            if(i < size) queryText += " and ";
        }
        TypedQuery<Fornecedor> typedQuery = manager.createQuery(queryText, Fornecedor.class);
        for(String s : atributes.keySet()){
            typedQuery.setParameter(s, atributes.get(s));
        }

        return typedQuery.getResultList();
    }

    public List<Fornecedor> findByAttribute(String attribute, String value){
        String queryText = "select f from Fornecedor f where f." + attribute + " like " + ":" + attribute;

        TypedQuery<Fornecedor> typedQuery = manager.createQuery(queryText, Fornecedor.class);
        typedQuery.setParameter(attribute, value);

        return typedQuery.getResultList();
    }

    public void save(Fornecedor fornecedor) {
        manager.getTransaction().begin();
        manager.persist(fornecedor);
        manager.getTransaction().commit();
    }

    public void delete(Fornecedor fornecedor){
        manager.getTransaction().begin();
        manager.remove(fornecedor);
        manager.getTransaction().commit();
    }

    public void update(Fornecedor fornecedor){
        try{
            manager.getTransaction().begin();
            manager.merge(fornecedor);
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            System.out.println("Erro ao atualizar entidade");
        }
    }

}

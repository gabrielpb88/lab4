package br.gov.sp.fatec.lab4.dao;

import br.gov.sp.fatec.lab4.entities.Item;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class ItemDao {

    private EntityManager manager = PersistenceManager.getInstance().getEntityManager();

    public Item findById(Long id){
        return manager.find(Item.class, id);
    }

    public List<Item> findByAttributes(Map<String, String> atributes){
        if (atributes == null) return null;

        String queryText = "select i from Item i where ";
        int size = atributes.size();
        int i = 0;
        for(String s : atributes.keySet()){
            queryText += "i." + s + " like :" + s;
            i++;
            if(i < size) queryText += " and ";
        }
        TypedQuery<Item> typedQuery = manager.createQuery(queryText, Item.class);
        for(String s : atributes.keySet()){
            typedQuery.setParameter(s, atributes.get(s));
        }

        return typedQuery.getResultList();
    }

    public List<Item> findByAttribute(String attribute, String value){
        String queryText = "select i from Item i where i." + attribute + " like " + ":" + attribute;

        TypedQuery<Item> typedQuery = manager.createQuery(queryText, Item.class);
        typedQuery.setParameter(attribute, value);

        return typedQuery.getResultList();
    }

    public void save(Item item) {
        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();
    }

    public void delete(Item item){
        manager.getTransaction().begin();
        manager.remove(item);
        manager.getTransaction().commit();
    }

    public void update(Item item){
        try{
            manager.getTransaction().begin();
            manager.merge(item);
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            System.out.println("Erro ao atualizar entidade");
        }
    }

}

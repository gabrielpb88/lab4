package br.gov.sp.fatec.lab4.dao;

import br.gov.sp.fatec.lab4.entities.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class PedidoDao {

    private EntityManager manager = PersistenceManager.getInstance().getEntityManager();

    public Pedido findById(Long id){
        return manager.find(Pedido.class, id);
    }

    public List<Pedido> findByAttributes(Map<String, String> atributes){
        if (atributes == null) return null;

        String queryText = "select p from Pedido p where ";
        int size = atributes.size();
        int i = 0;
        for(String s : atributes.keySet()){
            queryText += "p." + s + " like :" + s;
            i++;
            if(i < size) queryText += " and ";
        }
        TypedQuery<Pedido> typedQuery = manager.createQuery(queryText, Pedido.class);
        for(String s : atributes.keySet()){
            typedQuery.setParameter(s, atributes.get(s));
        }

        return typedQuery.getResultList();
    }

    public List<Pedido> findByAttribute(String attribute, String value){
        String queryText = "select p from Pedido p where p." + attribute + " like " + ":" + attribute;

        TypedQuery<Pedido> typedQuery = manager.createQuery(queryText, Pedido.class);
        typedQuery.setParameter(attribute, value);

        return typedQuery.getResultList();
    }

    public void save(Pedido pedido) {
        manager.getTransaction().begin();
        manager.persist(pedido);
        manager.getTransaction().commit();
    }

    public void delete(Pedido pedido){
        manager.getTransaction().begin();
        manager.remove(pedido);
        manager.getTransaction().commit();
    }

    public void update(Pedido pedido){
        try{
            manager.getTransaction().begin();
            manager.merge(pedido);
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            System.out.println("Erro ao atualizar entidade");
        }
    }

}

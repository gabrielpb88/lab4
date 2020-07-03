package br.gov.sp.fatec.lab4.dao;

import br.gov.sp.fatec.lab4.entities.Pagamento;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class PagamentoDao {

    private EntityManager manager;

    public PagamentoDao() {
        this.manager = PersistenceManager.getInstance().getEntityManager();
    }

    public PagamentoDao(EntityManager manager) {
        this.manager = PersistenceManager.getInstance().getEntityManager();
    }

    public Pagamento findById(Long id) {
        return manager.find(Pagamento.class, id);
    }

    public List<Pagamento> findByAttributes(Map<String, String> atributes) {
        if (atributes == null) return null;

        String queryText = "select p from Pagamento p where ";
        int size = atributes.size();
        int i = 0;
        for(String s : atributes.keySet()){
            queryText += "p." + s + " like :" + s;
            i++;
            if(i < size) queryText += " and ";
        }
        TypedQuery<Pagamento> typedQuery = manager.createQuery(queryText, Pagamento.class);
        for(String s : atributes.keySet()){
            typedQuery.setParameter(s, atributes.get(s));
        }

        return typedQuery.getResultList();
    }

    public List<Pagamento> findByAttribute(String attribute, String value){
        String queryText = "select p from Pagamento p where p." + attribute + " like " + ":" + attribute;

        TypedQuery<Pagamento> typedQuery = manager.createQuery(queryText, Pagamento.class);
        typedQuery.setParameter(attribute, value);

        return typedQuery.getResultList();
    }

    public void save(Pagamento pagamento) {
        manager.getTransaction().begin();
        manager.persist(pagamento);
        manager.getTransaction().commit();
    }

    public void delete(Pagamento pagamento){
        manager.getTransaction().begin();
        manager.remove(pagamento);
        manager.getTransaction().commit();
    }

    public void update(Pagamento pagamento){
        try{
            manager.getTransaction().begin();
            manager.merge(pagamento);
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            System.out.println("Erro ao atualizar entidade");
        }
    }

}

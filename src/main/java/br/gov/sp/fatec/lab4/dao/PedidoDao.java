package br.gov.sp.fatec.lab4.dao;

import br.gov.sp.fatec.lab4.entities.Cliente;
import br.gov.sp.fatec.lab4.entities.Item;
import br.gov.sp.fatec.lab4.entities.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class PedidoDao {

    //private EntityManager manager = PersistenceManager.getInstance().getEntityManager();
    private EntityManager manager;
    private ItemDao itemDao;
    private ClienteDao clienteDao;
    public Pedido findById(Long id) {
        return manager.find(Pedido.class, id);
    }
    public PedidoDao(EntityManager manager) {
        this.manager = manager;
        // Usamos o mesmo manager nos outros DAOs para manter
        // tudo no mesmo contexto
        itemDao = new ItemDao(manager);
        clienteDao = new ClienteDao(manager);
    }
    public void salvar(Pedido pedido) throws RollbackException {
        try {
			//sempre ira gerar um novo pedido
			pedido.setId(null);
            manager.getTransaction().begin();
            salvarSemCommit(pedido);
            manager.getTransaction().commit();
        }
        catch(RollbackException e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    private void salvarSemCommit(Pedido pedido) {
    	if(pedido.getCliente().getId() == null) {
            clienteDao.salvarSemCommit(pedido.getCliente());
	    }
		
		for(Item item: pedido.getItems()){
			if(item.getId()==null){
				itemDao.salvarSemCommit(item);
			}
		}
		//persiste o dado 
		manager.persist(pedido);
		
	}

	public List<Pedido> findPedidosComItemDoCliente(Cliente cliente, String item) {
        if (cliente == null || item == null || item.trim().length() == 0) return null;

        String queryText = "select p from Pedido p "
                + "inner join p.items i "
                + "inner join p.cliente c "
                + "where i.nome like :nome "
                + "and c.id = :id";

        TypedQuery<Pedido> typedQuery = manager.createQuery(queryText, Pedido.class);
        typedQuery.setParameter("nome", item);
        typedQuery.setParameter("id", cliente.getId());

        return typedQuery.getResultList();
    }

    public List<Pedido> findByAttributes(Map<String, String> atributes) {
        if (atributes == null) return null;

        StringBuilder queryText = new StringBuilder("select p from Pedido p where ");
        int size = atributes.size();
        int i = 0;
        for (String s : atributes.keySet()) {
            queryText.append("p." + s + " like :" + s);
            i++;
            if (i < size) queryText.append(" and ");
        }
        TypedQuery<Pedido> typedQuery = manager.createQuery(queryText.toString(), Pedido.class);
        for (String s : atributes.keySet()) {
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

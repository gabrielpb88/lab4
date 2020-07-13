package br.gov.sp.fatec.lab4.dao;

import br.gov.sp.fatec.lab4.entitie.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsuarioDao {

    private EntityManager manager;

    public UsuarioDao() {
        this.manager = PersistenceManager.getInstance().getEntityManager();
    }

    public UsuarioDao(EntityManager manager) {
        this.manager = manager;
    }

    /**
     *
     * @param usuario Usuario a ser buscado
     * @return Instancia de Usuario
     */
    public Usuario findByusuario(String usuario){
        List<Usuario> retornoDoBanco = findByAttribute("usuario", usuario);
        return retornoDoBanco.size() > 0 ? retornoDoBanco.get(0) : null;
    }

    public List<Usuario> findByAttribute(String attribute, String value){
        String queryText = "select c from Usuario c where c." + attribute + " like " + ":" + attribute;

        TypedQuery<Usuario> typedQuery = manager.createQuery(queryText, Usuario.class);
        typedQuery.setParameter(attribute, value);

        return typedQuery.getResultList();
    }

    public void save(Usuario Usuario) {
        manager.getTransaction().begin();
        manager.persist(Usuario);
        manager.getTransaction().commit();
    }

    public void delete(Usuario Usuario){
        manager.getTransaction().begin();
        manager.remove(Usuario);
        manager.getTransaction().commit();
    }

    public void update(Usuario Usuario){
        try {
            manager.getTransaction().begin();
            manager.merge(Usuario);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Erro ao atualizar entidade");
        }
    }


}

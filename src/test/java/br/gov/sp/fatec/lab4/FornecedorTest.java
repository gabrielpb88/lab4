package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entities.Fornecedor;
import br.gov.sp.fatec.lab4.entities.Item;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FornecedorTest {

    private EntityManager manager;
    private Fornecedor fornecedor;
    private Item item;

    @Test
    public void testeSalvarItems()
    {
        manager = PersistenceManager
                .getInstance().getEntityManager();

        item = new Item();
        item.setNome("Moto G5");
        item.setPreco(600.00);
        fornecedor = new Fornecedor();
        fornecedor.setCnpj("123456789000101");
        fornecedor.setNome("Fatec");
        item.setFornecedor(fornecedor);
        fornecedor.getItems().add(item);

        manager.getTransaction().begin();
        manager.persist(fornecedor);
        manager.persist(item);
        manager.getTransaction().commit();

        assertTrue( true );
    }

    @Test
    public void testeSalvarFornecedor()
    {
        manager = PersistenceManager
                .getInstance().getEntityManager();

        fornecedor = new Fornecedor();
        fornecedor.setCnpj("123");
        fornecedor.setNome("Distribuidora do Vale");

        manager.getTransaction().begin();
        manager.persist(fornecedor);
        manager.getTransaction().commit();

        assertTrue( true );
    }

}

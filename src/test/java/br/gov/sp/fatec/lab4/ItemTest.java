package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entities.Fornecedor;
import br.gov.sp.fatec.lab4.entities.Item;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    private EntityManager manager;
    private Item item;
    private Fornecedor fornecedor;

    @Test
    public void testeSalvarItem()
    {
        manager = PersistenceManager
                .getInstance().getEntityManager();
        fornecedor = new Fornecedor();
        fornecedor.setCnpj("123");
        fornecedor.setNome("Distribuidora do Vale");

        item = new Item();
        item.setNome("Moto G5");
        item.setPreco(600.00);
        item.setFornecedor(fornecedor);

        manager.getTransaction().begin();
        manager.persist(fornecedor);
        manager.persist(item);
        manager.getTransaction().commit();

        assertTrue( true );
    }

}

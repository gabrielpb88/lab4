package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entities.ClientePF;
import br.gov.sp.fatec.lab4.entities.ClientePJ;
import br.gov.sp.fatec.lab4.entities.Fornecedor;
import br.gov.sp.fatec.lab4.entities.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FornecedorTest {

    private EntityManager manager;
    private Fornecedor fornecedor;
    private Item item;

    @BeforeAll
    public void setUp(){
        manager = PersistenceManager
                .getInstance().getEntityManager();

        item = new Item();
        item.setNome("Moto G5");
        item.setPreco(600.00);
    }

    @Test
    public void testeSalvarItems()
    {
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
        fornecedor = new Fornecedor();
        fornecedor.setCnpj("123");
        fornecedor.setNome("Distribuidora do Vale");

        manager.getTransaction().begin();
        manager.persist(fornecedor);
        manager.getTransaction().commit();

        assertTrue( true );
    }

}

package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entities.ClientePF;
import br.gov.sp.fatec.lab4.entities.ClientePJ;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteTest {

    private EntityManager manager;
    private ClientePF clientePF;
    private ClientePJ clientePJ;

    @BeforeAll
    public void setUp(){
        manager = PersistenceManager
                .getInstance().getEntityManager();
    }

    @Test
    public void testeSalvarClientePF()
    {
        clientePF = new ClientePF();
        clientePF.setCpf("123");
        clientePF.setNome("Gabriel");
        clientePF.setEndereco("Rua A");

        manager.getTransaction().begin();
        manager.persist(clientePF);
        manager.getTransaction().commit();

        assertTrue( true );
    }

    @Test
    public void testeSalvarClientePJ()
    {
        clientePJ = new ClientePJ();
        clientePJ.setCnpj("123456789000101");
        clientePJ.setNome("Fatec");
        clientePJ.setEndereco("Fim do mundo");

        manager.getTransaction().begin();
        manager.persist(clientePJ);
        manager.getTransaction().commit();

        assertTrue( true );
    }

}

package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entitie.ClientePF;
import br.gov.sp.fatec.lab4.entitie.ClientePJ;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClienteTest {

    private EntityManager manager;
    private ClientePF clientePF;
    private ClientePJ clientePJ;

    @Test
    public void testeSalvarClientePF()
    {
        manager = PersistenceManager
                .getInstance().getEntityManager();

        clientePF = new ClientePF();
        clientePF.setCpf("283");
        clientePF.setNome("Maria");
        clientePF.setEndereco("Rua B");

        manager.getTransaction().begin();
        manager.persist(clientePF);
        manager.getTransaction().commit();

        assertNotNull(clientePF.getId());

    }

    @Test
    public void testeSalvarClientePJ()
    {
        manager = PersistenceManager
                .getInstance().getEntityManager();

        clientePJ = new ClientePJ();
        clientePJ.setCnpj("123456789000101");
        clientePJ.setNome("Fatec");
        clientePJ.setEndereco("Fim do mundo");

        manager.getTransaction().begin();
        manager.persist(clientePJ);
        manager.getTransaction().commit();

        assertNotNull(clientePJ.getId());
    }

}

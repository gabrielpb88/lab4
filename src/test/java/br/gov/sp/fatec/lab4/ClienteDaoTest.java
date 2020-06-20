package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.ClienteDao;
import br.gov.sp.fatec.lab4.entities.Cliente;
import br.gov.sp.fatec.lab4.entities.ClientePF;
import br.gov.sp.fatec.lab4.entities.ClientePJ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDaoTest {

    private ClienteDao dao = new ClienteDao();
    private ClientePF clientePF;
    private ClientePJ clientePJ;

    @Test
    public void testeClientePF()
    {
        clientePF = new ClientePF();
        clientePF.setCpf("123");
        clientePF.setNome("Gabriel");
        clientePF.setEndereco("Rua A");

        dao.salvar(clientePF);
        assertNotNull(clientePF.getId());

        clientePF.setNome("Gabriel Pereira");
        dao.atualizar(clientePF);
        assertEquals("Gabriel Pereira", clientePF.getNome());

        Cliente cliente = dao.buscar(1L);
        assertEquals("Rua A", cliente.getEndereco());

        dao.deletar(cliente);
        Cliente clienteBuscado = dao.buscar(1L);
        assertNull(clienteBuscado);
    }

    @Test
    public void testeSalvarClientePJ()
    {
        clientePJ = new ClientePJ();
        clientePJ.setCnpj("123456789000101");
        clientePJ.setNome("Fatec");
        clientePJ.setEndereco("Fim do mundo");

        dao.salvar(clientePJ);

        assertNotNull(clientePJ.getId());

        dao.salvar(clientePJ);
        assertNotNull(clientePJ.getId());

        clientePJ.setNome("Gabriel Pereira");
        dao.atualizar(clientePJ);
        assertEquals("Gabriel Pereira", clientePJ.getNome());

        Cliente cliente = dao.buscar(clientePJ.getId());
        assertEquals("Fim do mundo", cliente.getEndereco());

        dao.deletar(cliente);
        Cliente clienteBuscado = dao.buscar(cliente.getId());
        assertNull(clienteBuscado);
    }

}

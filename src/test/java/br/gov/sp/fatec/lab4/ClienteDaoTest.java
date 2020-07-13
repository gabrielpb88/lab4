package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.ClienteDao;
import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entitie.Cliente;
import br.gov.sp.fatec.lab4.entitie.ClientePF;
import br.gov.sp.fatec.lab4.entitie.ClientePJ;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteDaoTest {
    private static EntityManager manager = PersistenceManager.getInstance().getEntityManager();

    private static ClienteDao dao;
    private static ClientePF clientePF;
    private static ClientePJ clientePJ;

    @BeforeAll
    public static void setUp(){
        dao = new ClienteDao(manager);
        clientePJ = new ClientePJ();
        clientePF = new ClientePF();
    }

    @Test
    @Order(1)
    public void salvandoClientes()
    {
        clientePF.setCpf("123");
        clientePF.setNome("Gabriel Pereira");
        clientePF.setEndereco("Rua A");

        clientePJ.setCnpj("123");
        clientePJ.setNome("Gabriel Pereira Bastos");
        clientePJ.setEndereco("Rua A");

        dao.save(clientePF);
        dao.save(clientePJ);

        assertNotNull(clientePF.getId());
        assertNotNull(clientePJ.getId());

    }

    @Test
    @Order(2)
    public void testandoBuscaPorId(){
        Cliente cli = new ClientePF("123456789");
        dao.save(cli);
        Cliente clienteBuscado = dao.findById(cli.getId());
        assertNotNull(clienteBuscado);
    }

    @Test
    @Order(3)
    public void buscandoPorUmAtributoSemCoringa(){
        List<Cliente> result = dao.findByAttribute("nome", "abr");
        assertEquals(0, result.size());
    }

    @Test
    @Order(3)
    public void buscandoPorUmAtributoComCoringa(){
        List<Cliente> result = dao.findByAttribute("nome", "%abr%");
        assertEquals(2, result.size());
    }

    @Test
    @Order(3)
    public void buscandoPorVariosAtributos(){
        Map<String, String> map = new HashMap<>();
        map.put("nome", "%abr%");
        map.put("endereco", "%a%");

        List<Cliente> result = dao.findByAttributes(map);
        assertEquals(2, result.size());
    }

    @Test
    @Order(4)
    public void atualizandoCliente(){
        clientePF.setNome("Gabriel");
        dao.update(clientePF);

        Cliente clienteBuscado = dao.findById(clientePF.getId());

        assertEquals("Gabriel", clienteBuscado.getNome());
    }

    @Test
    @Order(5)
    public void deletandoCliente(){
        long id = clientePF.getId();
        dao.delete(clientePF);
        Cliente naoEncontrado = dao.findById(id);
        assertNull(naoEncontrado);
    }

}

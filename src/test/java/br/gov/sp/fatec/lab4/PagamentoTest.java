package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entities.PagamentoCartao;
import br.gov.sp.fatec.lab4.entities.PagamentoDinheiro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PagamentoTest {

    private EntityManager manager;
    private PagamentoCartao pagamentoCartao;
    private PagamentoDinheiro pagamentoDinheiro;

    @BeforeAll
    public void setUp(){
        manager = PersistenceManager
                .getInstance().getEntityManager();
    }

    @Test
    public void testePagamentoCartao()
    {
        pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setValor(5000.00);
        pagamentoCartao.setParcelas(10);

        manager.getTransaction().begin();
        manager.persist(pagamentoCartao);
        manager.getTransaction().commit();

        assertTrue( true );
    }

    @Test
    public void testePagamentoDinheiro()
    {
        pagamentoDinheiro = new PagamentoDinheiro();
        pagamentoDinheiro.setValor(2000.00);
        pagamentoDinheiro.setDesconto(5.0);

        manager.getTransaction().begin();
        manager.persist(pagamentoDinheiro);
        manager.getTransaction().commit();

        assertTrue( true );
    }

}

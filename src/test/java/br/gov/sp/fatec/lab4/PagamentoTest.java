package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entitie.PagamentoCartao;
import br.gov.sp.fatec.lab4.entitie.PagamentoDinheiro;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PagamentoTest {

    private EntityManager manager;
    private PagamentoCartao pagamentoCartao;
    private PagamentoDinheiro pagamentoDinheiro;

    @Test
    public void testePagamentoCartao()
    {
        manager = PersistenceManager
                .getInstance().getEntityManager();

        pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setValor(5000.00);
        pagamentoCartao.setParcelas(10);

        manager.getTransaction().begin();
        manager.persist(pagamentoCartao);
        manager.getTransaction().commit();

        assertNotNull(pagamentoCartao.getId());
    }

    @Test
    public void testePagamentoDinheiro()
    {

        manager = PersistenceManager
                .getInstance().getEntityManager();

        pagamentoDinheiro = new PagamentoDinheiro();
        pagamentoDinheiro.setValor(2000.00);
        pagamentoDinheiro.setDesconto(5.0);

        manager.getTransaction().begin();
        manager.persist(pagamentoDinheiro);
        manager.getTransaction().commit();

        assertNotNull(pagamentoDinheiro.getId());
    }

}

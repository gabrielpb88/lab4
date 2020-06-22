package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entities.*;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PedidoTest {

    private EntityManager manager;
    private Cliente cliente;
    private Fornecedor fornecedor;
    private Item item;
    private Pedido pedido;
    private Pagamento pagamento;

    @Test
    public void testeSalvarPedido()
    {
        manager = PersistenceManager
                .getInstance().getEntityManager();

        pedido = new Pedido();

        cliente = new ClientePF("311");
        cliente.setNome("Marcelo");

        fornecedor = new Fornecedor();
        fornecedor.setNome("Distribuidor do Vale");
        fornecedor.setCnpj("321");

        item = new Item();
        item.setFornecedor(fornecedor);
        item.setNome("Celular Moto G5");
        item.setPreco(500.00);

        pagamento = new PagamentoDinheiro();
        pagamento.setValor(500.00);
        pagamento.setPedido(pedido);

        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.persist(fornecedor);
        manager.persist(item);
        manager.getTransaction().commit();

        pedido.setCliente(cliente);
        pedido.getItems().add(item);
        pedido.getPagamentos().add(pagamento);

        manager.getTransaction().begin();
        manager.persist(pedido);
        manager.persist(pagamento);
        manager.getTransaction().commit();

        assertNotNull(pedido.getId());
    }

}
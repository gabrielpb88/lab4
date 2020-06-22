package br.gov.sp.fatec.lab4;

import br.gov.sp.fatec.lab4.dao.ClienteDao;
import br.gov.sp.fatec.lab4.dao.FornecedorDao;
import br.gov.sp.fatec.lab4.dao.ItemDao;
import br.gov.sp.fatec.lab4.dao.PedidoDao;
import br.gov.sp.fatec.lab4.entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoDaoTest {

    private static PedidoDao pedidoDao;
    private static ClienteDao clienteDao;
    private static FornecedorDao fornecedorDao;
    private static ItemDao itemDao;

    private static Cliente cliente;
    private static Fornecedor fornecedor;
    private static Item item;
    private static Pedido pedido;

    @BeforeAll
    public static void setUp(){
        pedidoDao = new PedidoDao();
        clienteDao = new ClienteDao();
        fornecedorDao = new FornecedorDao();
        itemDao = new ItemDao();

        pedido = new Pedido();

        cliente = new ClientePF("311");
        cliente.setNome("Marcelo");

        fornecedor = new Fornecedor();
        fornecedor.setNome("Distribuidor do Vale");
        fornecedor.setCnpj("12.345.678/0009-10");

        item = new Item();
        item.setNome("Celular Moto G5");
        item.setPreco(500.00);
        item.setFornecedor(fornecedor);

        fornecedor.getItems().add(item);

        pedido.setCliente(cliente);
        pedido.getItems().add(item);

        clienteDao.save(cliente);
        fornecedorDao.save(fornecedor);
        itemDao.save(item);
        pedidoDao.save(pedido);
    }

    @Test
    public void findPedidosComItemDoCliente()
    {
        List<Pedido> pedidos = pedidoDao.findPedidosComItemDoCliente(cliente, "%Celular%");
        assertEquals(1, pedidos.size());

        List<Pedido> listaVazia = pedidoDao.findPedidosComItemDoCliente(cliente, "item que não existe no banco");
        assertEquals(0, listaVazia.size());
    }

}
package br.gov.sp.fatec.lab4.controller;

import br.gov.sp.fatec.lab4.dao.ClienteDao;
import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entitie.Cliente;
import br.gov.sp.fatec.lab4.entitie.ClientePF;
import br.gov.sp.fatec.lab4.entitie.ClientePJ;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClienteController extends HttpServlet {

    private ObjectMapper mapper;
    private ClienteDao dao;
    private EntityManager manager;

    public ClienteController(){
        manager = PersistenceManager.getInstance().getEntityManager();
        mapper = new ObjectMapper();
        dao = new ClienteDao(manager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String json = null;
        Cliente cliente = dao.findById(id);
        if(cliente == null){
            resp.setStatus(404);
        } else {
            resp.setStatus(200);
            json = mapper.writeValueAsString(cliente);
            resp.getWriter().print(json);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente cliente;
        String tipoCliente = req.getParameter("tipo").toLowerCase();

        switch (tipoCliente){
            case "pf":
                cliente = mapper.readValue(req.getReader(), ClientePF.class);
                break;
            case "pj":
                cliente = mapper.readValue(req.getReader(), ClientePJ.class);
                break;
            default:
                cliente = null;
        }

        try {
            dao.save(cliente);
            String json = mapper.writeValueAsString(cliente);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
                    + "/cliente?id=" + cliente.getId();
            resp.setHeader("Location", location);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(json);
            resp.getWriter().flush();

        } catch (Exception e){
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("id") == null){
            resp.setStatus(404);
            resp.getWriter().flush();
            return;
        }

        try{
            ClientePF cliente = mapper.readValue(req.getReader(), ClientePF.class);
            Long id = Long.valueOf(req.getParameter("id"));

            Cliente clienteJPA = dao.findById(id);
            cliente.setId(clienteJPA.getId());
            dao.update(cliente);
            resp.setStatus(204);
            String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
                    + "/cliente?id=" + cliente.getId();
            resp.setHeader("Location", location);
            resp.getWriter().flush();

        }catch (Exception e){
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Cliente cliente = dao.findById(id);
        if(cliente == null){
            resp.setStatus(404);
        } else {
            dao.delete(cliente);
            resp.setStatus(204);
        }
        resp.getWriter().flush();
    }
}

package br.gov.sp.fatec.lab4.controller;

import br.gov.sp.fatec.lab4.dao.ClienteDao;
import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entities.Cliente;
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
        resp.getWriter().println("Ok");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cliente cliente = mapper.readValue(req.getReader(), Cliente.class);
        try{
            dao.save(cliente);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            String location = req.getServerName() + ":" + req.getServerPort() + "/" + req.getContextPath()
                    + "/cliente/" + String.valueOf(cliente.getId());
            resp.setHeader("location", location);
        }catch (Exception e){
            resp.setStatus(500);
        }
    }
}

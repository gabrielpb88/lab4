package br.gov.sp.fatec.lab4.controller;

import br.gov.sp.fatec.lab4.dao.FornecedorDao;
import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.entitie.Fornecedor;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FornecedorController extends HttpServlet {

    private ObjectMapper mapper;
    private FornecedorDao dao;
    private EntityManager manager;

    public FornecedorController(){
        manager = PersistenceManager.getInstance().getEntityManager();
        mapper = new ObjectMapper();
        dao = new FornecedorDao(manager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String json = null;
        Fornecedor fornecedor = dao.findById(id);
        if(fornecedor == null){
            resp.setStatus(404);
        } else {
            resp.setStatus(200);
            json = mapper.writeValueAsString(fornecedor);
            resp.getWriter().print(json);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Fornecedor fornecedor = mapper.readValue(req.getReader(), Fornecedor.class);

        try {
            dao.save(fornecedor);
            String json = mapper.writeValueAsString(fornecedor);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
                    + "/fornecedor?id=" + fornecedor.getId();
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

        try{
            Fornecedor fornecedor = mapper.readValue(req.getReader(), Fornecedor.class);
            if(fornecedor.getId() == null){
                resp.setStatus(404);
                resp.getWriter().flush();
                return;
            }
            dao.update(fornecedor);
            resp.setStatus(204);
            String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
                    + "/fornecedor?id=" + fornecedor.getId();
            resp.setHeader("Location", location);
            resp.getWriter().flush();

        }catch (Exception e){
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Long id = Long.valueOf(req.getParameter("id"));
            Fornecedor fornecedor = dao.findById(id);
            if(fornecedor == null){
                resp.setStatus(404);
            } else {
                dao.delete(fornecedor);
                resp.setStatus(204);
            }
        }
        catch (Exception e){
            resp.setStatus(404);
        }
        resp.getWriter().flush();
    }
}

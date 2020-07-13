package br.gov.sp.fatec.lab4.controller;

import br.gov.sp.fatec.lab4.dao.PersistenceManager;
import br.gov.sp.fatec.lab4.dao.UsuarioDao;
import br.gov.sp.fatec.lab4.entitie.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsuarioController extends HttpServlet {

    private ObjectMapper mapper;
    private UsuarioDao dao;
    private EntityManager manager;

    public UsuarioController(){
        manager = PersistenceManager.getInstance().getEntityManager();
        mapper = new ObjectMapper();
        dao = new UsuarioDao(manager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("usuario");
        Usuario usuario = dao.findByusuario(param);
        String json = mapper.writeValueAsString(usuario);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(json);
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = mapper.readValue(req.getReader(), Usuario.class);
        try {
            dao.save(usuario);
            String json = mapper.writeValueAsString(usuario);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
                    + "/usuario?usuario=" + usuario.getUsuario();
            resp.setHeader("Location", location);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(json);
            resp.getWriter().flush();

        } catch (Exception e){
            resp.setStatus(500);
            e.printStackTrace();
        }
    }

}

package br.gov.sp.fatec.lab4.controller;

import br.gov.sp.fatec.lab4.dao.UsuarioDao;
import br.gov.sp.fatec.lab4.entitie.Usuario;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class AuthFilter implements Filter {

    private ServletContext context;
    private UsuarioDao dao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.dao = new UsuarioDao();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if(req.getMethod().equals("POST") && req.getServletPath().equals("/usuario")){
            filterChain.doFilter(req, res);
            return ;
        }

        Usuario usuario;
        List<String> usr = extractUser(req);
        if( usr != null && usr.size() > 1){
            usuario = dao.findByusuario(usr.get(0).toString());

            if(usuario == null){
                unauthorized(res, "Usuário não encontrado!");
                return ;
            }
            if(!usr.get(1).equals(usuario.getSenha())){
                unauthorized(res, "Senha incorreta!");
                return ;
            }
        } else {
            unauthorized(res, "Invalid Authentication token");
            return ;
        }

        if((req.getMethod().equals("PUT") || req.getMethod().equals("PATCH") ||
                req.getMethod().equals("DELETE")) && !usuario.hasHole("ADMIN")){
            res.sendError(403, "Permissão negada!");
        } else {
            filterChain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }

    private List<String> extractUser(HttpServletRequest req){
        String authHeader = req.getHeader("Authorization");
        if(authHeader != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(authHeader);

            if (stringTokenizer.hasMoreTokens()) {
                String basic = stringTokenizer.nextToken();
                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        String credentials = new String(Base64.getDecoder()
                                .decode(stringTokenizer.nextToken()));

                        Integer p = credentials.indexOf(":");
                        if (p != -1) {
                            String username = credentials.substring(0, p).trim();
                            String password = credentials.substring(p + 1).trim();

                            if (username == null || password == null || username.length() == 0 || password.length() == 0) {
                                return null;
                            }
                            return Arrays.asList(username, password);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }


    private void unauthorized(HttpServletResponse res, String message) throws IOException {
        res.setHeader("WWW-Authenticate", "Basic realm=\"" + "PROTECTED" + "\"");
        res.sendError(401, message);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scpexsto.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import scpexsto.model.entity.Usuario;

@WebFilter(filterName = "GerenciaPagesFilter", urlPatterns = {"/protected/gerencia/*"})
public class GerenciaPagesFilter extends AbstractFilter implements Filter {
 
    @Override
    public void destroy() {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Usuario usuario = (Usuario) req.getSession(true).getAttribute("usuario");
 
        if (!usuario.getPerfil().equals(Usuario.PERFIL_GERENTE)) {
            accessDenied(request, response, req);
            return;
        }
 
        chain.doFilter(request, response);
    }
 
    @Override
    public void init(FilterConfig arg0) throws ServletException {
 
    }
}
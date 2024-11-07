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

@WebFilter(filterName = "ProducaoPagesFilter", urlPatterns = {"/protected/producao/*"})
public class ProducaoPagesFilter extends AbstractFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Usuario usuario = (Usuario) req.getSession(true).getAttribute("usuario");

        if (!usuario.getPerfil().equals(Usuario.PERFIL_GERENTE)
                && !usuario.getPerfil().equals(Usuario.PERFIL_LIDER)
                && !usuario.getPerfil().equals(Usuario.PERFIL_PRODUCAO)) {

            accessDenied(request, response, req);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}

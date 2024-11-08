package scpexsto.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import scpexsto.model.entity.Usuario;

@WebFilter(filterName = "LoginFilter", initParams = {@WebInitParam(name = "loginActionURI", value = "/public/login.xhtml")},
        urlPatterns = {"/*"})
public class LoginCheckFilter extends AbstractFilter implements Filter {

    private static List<String> allowedURIs;

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        if (allowedURIs == null) {
            allowedURIs = new ArrayList<String>();
            allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
            allowedURIs.add("/ScpExsto/javax.faces.resource/main.css.xhtml");
            allowedURIs.add("/ScpExsto/javax.faces.resource/theme.css.xhtml");
            allowedURIs.add("/ScpExsto/javax.faces.resource/primefaces.js.xhtml");
            allowedURIs.add("/ScpExsto/javax.faces.resource/primefaces.css.xhtml");
            allowedURIs.add("/ScpExsto/javax.faces.resource/jquery/jquery.js.xhtml");
            allowedURIs.add("/ScpExsto/javax.faces.resource/messages/messages.png.xhtml");
            allowedURIs.add("/ScpExsto/javax.faces.resource/images/ui-icons_2e83ff_256x240.png.xhtml");
            allowedURIs.add("/ScpExsto/javax.faces.resource/images/ui-icons_38667f_256x240.png.xhtml");
            allowedURIs.add("/ScpExsto/javax.faces.resource/images/logoexsto.jpg.xhtml");
            allowedURIs.add("/ScpExsto/resources/images/logoexsto.jpg");
        }
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if (session.isNew()) {
            doLogin(request, response, req);
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null && !allowedURIs.contains(req.getRequestURI())) {
            System.out.println(req.getRequestURI());
            doLogin(request, response, req);
            return;
        }

        chain.doFilter(request, response);
    }
}

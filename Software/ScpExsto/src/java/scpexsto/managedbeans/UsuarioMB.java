package scpexsto.managedbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import scpexsto.model.ServiceLocator;

import scpexsto.model.entity.Usuario;

@ViewScoped
@ManagedBean(name = "usuarioMB")
public class UsuarioMB extends AbstractMB implements Serializable {

    public static final String INJECTION_NAME = "#{usuarioMB}";
    private static final long serialVersionUID = 1L;

    private List<String> perfis;

    private Usuario usuario;
    private List<Usuario> lista;
    private String senha;

    public UsuarioMB() {
        this.perfis = Usuario.getListaPerfis();
    }

    public boolean isAdmin() {
        return usuario.isAdmin();
    }

    public boolean isDefaultUser() {
        return true;
    }

    public String logOut() {
        getRequest().getSession().invalidate();
        return "/pages/public/login.xhtml";
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getTodosUsuarios() {
        if (this.lista == null) {
            this.lista = ServiceLocator.getUsuarioService().readAll();
        }

        return lista;
    }

    public void getLista() {
        this.lista = ServiceLocator.getUsuarioService().readAll();
    }

    public void ressetUsuario() {
        this.usuario = new Usuario();
    }

    public void setUsuario(Usuario u) {
        this.usuario = u;
    }

    public List<String> getPerfis() {
        return perfis;
    }

    public void updateUsuario() {
        try {
            List<String> erros = ServiceLocator.getUsuarioService().validaCampos(usuario);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getUsuarioService().validaLoginExistente(usuario)) {
                    ServiceLocator.getUsuarioService().update(usuario);
                    closeDialog();
                    displayInfoMessageToUser("Atualizado com sucesso!");
                    getLista();
                    ressetUsuario();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O login já foi utilizado, tente outro login!");
                }
            } else {
                keepDialogOpen();
                for (Iterator<String> it = erros.iterator(); it.hasNext();) {
                    displayErrorMessageToUser(it.next());
                }
            }
        } catch (Exception e) {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("nome", usuario.getLogin());
            if (ServiceLocator.getUsuarioService().readByCriteria(criteria, "nome", true) != null) {
                keepDialogOpen();
                displayErrorMessageToUser("Erro! O login já foi utilizado, tente outro login!");
            }
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }

    public void createUsuario() {
        try {
            List<String> erros = ServiceLocator.getUsuarioService().validaCampos(usuario);

            if (!(erros.size() > 0)) {
                if (ServiceLocator.getUsuarioService().validaLoginExistente(usuario)) {
                    ServiceLocator.getUsuarioService().create(usuario);
                    closeDialog();
                    displayInfoMessageToUser("Usuario criado com sucesso!");
                    getLista();
                    ressetUsuario();
                } else {
                    keepDialogOpen();
                    displayErrorMessageToUser("Erro! O login já foi utilizado, tente outro login!");
                }
            } else {
                keepDialogOpen();
                for (Iterator<String> it = erros.iterator(); it.hasNext();) {
                    displayErrorMessageToUser(it.next());
                }
            }
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }

    public void deleteUsuario() {
        try {
            ServiceLocator.getUsuarioService().delete(usuario.getId());
            closeDialog();
            displayInfoMessageToUser("Usuario excluído com sucesso!");
            getLista();
            ressetUsuario();

//            ServiceLocator.getUsuarioService().update(usuario);
//            closeDialog();
//            displayInfoMessageToUser("Atualizado com sucesso!");
//            lista = this.getTodosUsuarios();
//            ressetUsuario();
        } catch (Exception e) {
            keepDialogOpen();
            displayErrorMessageToUser("Erro! Verifique os dados informados, ou tente mais tarde!");
            e.printStackTrace();
        }
    }

    public void mudaSenha() {
        this.updateUsuario();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

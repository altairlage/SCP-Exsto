package scpexsto.managedbeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import scpexsto.model.ServiceLocator;
import scpexsto.model.entity.Usuario;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB extends AbstractMB implements Serializable {

    private String username;
    private String senha;
    private String novaSenha;
    private String novaSenhaConfirm;

    private Usuario usuario;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String login() {
        Usuario usu = ServiceLocator.getUsuarioService().validarLogon(this.username, this.senha);

        if (usu != null) {
            //usuarioMB.setUsuario(usuario);
            this.usuario = usu;
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.getSession().setAttribute("usuario", this.usuario);
            return "/index";
        }

        displayErrorMessageToUser("Login ou senha inválidos!");

        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/public/login?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAcessoGerente() {
        if (this.usuario.getPerfil().equals(Usuario.PERFIL_GERENTE)) {
            return true;
        }
        return false;
    }

    public boolean isAcessoLider() {
        if (this.usuario.getPerfil().equals(Usuario.PERFIL_GERENTE)
                || this.usuario.getPerfil().equals(Usuario.PERFIL_LIDER)) {
            return true;
        }
        return false;
    }

    public boolean isAcessoProducao() {
        if (this.usuario.getPerfil().equals(Usuario.PERFIL_GERENTE)
                || this.usuario.getPerfil().equals(Usuario.PERFIL_LIDER)
                || this.usuario.getPerfil().equals(Usuario.PERFIL_PRODUCAO)) {
            return true;
        }
        return false;
    }

    public void mudaSenhaPublico() {
        if (this.getNovaSenha().equals(this.getNovaSenhaConfirm())) {
            this.usuario.setSenha(this.novaSenha);
            try {
                ServiceLocator.getUsuarioService().update(usuario);
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                request.getSession().setAttribute("usuario", this.usuario);
                displayInfoMessageToUser("Atualizado com sucesso!");
            } catch (Exception e) {
                displayErrorMessageToUser("Erro interno! Tente novamente!");
            }
        } else {
            displayErrorMessageToUser("Erro! As senhas não coincidem! Tente novamente.");
        }
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getNovaSenhaConfirm() {
        return novaSenhaConfirm;
    }

    public void setNovaSenhaConfirm(String novaSenhaConfirm) {
        this.novaSenhaConfirm = novaSenhaConfirm;
    }
}

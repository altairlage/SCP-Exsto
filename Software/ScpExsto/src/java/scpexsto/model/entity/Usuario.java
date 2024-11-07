package scpexsto.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "USUARIO")
//@NamedQuery(name = "Usuario.readByLogin", query = "select u from Usuario u where u.login = :login")
public class Usuario implements Serializable {

    //public static final String READ_BY_LOGIN = "Usuario.readByLogin";
    public static final String PERFIL_GERENTE = "Gerente de produção";
    public static final String PERFIL_LIDER = "Líder de produção";
    public static final String PERFIL_PRODUCAO = "Produção";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private long id;

    @Column(name = "LOGIN", nullable = false, unique = true, length = 50)
    private String login;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "NOME", nullable = false, length = 70)
    private String nome;

    @Column(name = "SENHA", nullable = false, length = 30)
    private String senha;

    @Column(name = "PERFIL", nullable = false)
    private String perfil;

    @Column(name = "ATIVO", nullable = false)
    private boolean ativo;

    public static List<String> getListaPerfis() {
        List<String> lista = new ArrayList<String>();
        lista.add(PERFIL_GERENTE);
        lista.add(PERFIL_LIDER);
        lista.add(PERFIL_PRODUCAO);

        return lista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String nivel) {
        this.perfil = nivel;
    }

    @Override
    public boolean equals(Object obj) {
        Usuario usr = (Usuario) obj;
        if (this.getId() == usr.getId() && this.getLogin().equals(usr.getLogin()) && this.getEmail().equals(usr.getLogin())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("id: ").append(this.getId());
        str.append(", login: ").append(this.getLogin());
        str.append(", nome: ").append(this.getNome());
        str.append(", email: ").append(this.getEmail());
        str.append(", perfil: ").append(this.getPerfil());

        return str.toString();
    }

    public boolean getAtivo() {
        return isAtivo();
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAdmin() {
        if (getPerfil().equals("ADMIN")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 89 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 89 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        return hash;
    }
}

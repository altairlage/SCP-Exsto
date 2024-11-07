package scpexsto.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import scpexsto.model.BaseService;
import scpexsto.model.JPAUtil;
import scpexsto.model.dao.UsuarioDao;
import scpexsto.model.entity.Usuario;

public class UsuarioService implements BaseService<Usuario> {

    private EntityManager em;

    @Override
    public void create(Usuario usuario) {
        em = JPAUtil.getInstance().getEntityManager();
        UsuarioDao dao = new UsuarioDao(em);

        em.getTransaction().begin();
        dao.create(usuario);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Usuario u) {
        em = JPAUtil.getInstance().getEntityManager();
        em.getTransaction().begin();

        UsuarioDao dao = new UsuarioDao(em);
        dao.update(u);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Usuario> readAll() {
        em = JPAUtil.getInstance().getEntityManager();
        UsuarioDao dao = new UsuarioDao(em);
        List<Usuario> usuarios = dao.readByCriteria(new HashMap<String, Object>(), "nome", false);
        em.close();

        //verificar se precisa abrir e dar commit na transação
        return usuarios;
    }

    @Override
    public List<Usuario> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        em = JPAUtil.getInstance().getEntityManager();
        UsuarioDao dao = new UsuarioDao(em);
        List<Usuario> usuarios = dao.readByCriteria(criteria, orderField, asc);
        em.close();

        //verificar se precisa abrir e dar commit na transação
        return usuarios;
    }

    public void delete(Long id) {
        em = JPAUtil.getInstance().getEntityManager();
        UsuarioDao dao = new UsuarioDao(em);
        em.getTransaction().begin();
        dao.Delete(id);
        em.getTransaction().commit();
        em.close();
    }

    public Usuario readById(Long Id) {
        em = JPAUtil.getInstance().getEntityManager();
        UsuarioDao dao = new UsuarioDao(em);

        Usuario usuario = dao.readById(Id);

        em.close();

        return usuario;
    }

    public Usuario validarLogon(String login, String senha) {
        if (login != null && senha != null) {
            if ((login.length() > 2) && (senha.length() > 2)) {
                em = JPAUtil.getInstance().getEntityManager();
                UsuarioDao dao = new UsuarioDao(em);
                Usuario usuario = dao.readByLogin(login);
                em.close();

                if (usuario != null) {
                    if (usuario.getSenha().equals(senha)) {
                        return usuario;
                    }
                }
            }
        }
        return null;
    }

    public Map<String, String> readPerfis() {
        Map<String, String> perfis = new HashMap<String, String>();
        perfis.put("Administrador", "ADMIN");
        perfis.put("Gerencia de produção", "GER_PRODUCAO");
        perfis.put("Lider de produção", "LIDER_PRODUCAO");
        perfis.put("Produção", "PRODUCAO");

        return perfis;
    }

    public List<String> validaCampos(Usuario usuario) {
        List<String> erros = new ArrayList<String>();

        if (usuario.getLogin().equals("") || usuario.getLogin().equals(" ") || usuario.getLogin() == null) {
            erros.add("Login vazio ou inválido!");
        }

        if (usuario.getEmail().equals("") || usuario.getEmail().equals(" ") || usuario.getEmail() == null) {
            erros.add("E-mail vazio ou inválido");
        }

        if (usuario.getNome().equals("") || usuario.getEmail().equals(" ") || usuario.getEmail() == null) {
            erros.add("Nome vazio ou inválido");
        }

        if (usuario.getPerfil() == null || usuario.getPerfil().equals("") || usuario.getPerfil().equals(" ")) {
            erros.add("Perfil inválido, selecione um perfil da lista.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().equals("") || usuario.getSenha().equals(" ")) {
            erros.add("A senha não pode ser vazia!");
        } else {
            if (usuario.getSenha().length() <= 3) {
                erros.add("A senha deve ter mais de 3 dígitos!");
            } else {
                if (usuario.getSenha().contains("'")
                        || usuario.getSenha().contains("*")
                        || usuario.getSenha().contains("&")
                        || usuario.getSenha().contains("%")
                        || usuario.getSenha().contains("$")
                        || usuario.getSenha().contains("+")
                        || usuario.getSenha().contains("~")
                        || usuario.getSenha().contains("/")
                        || usuario.getSenha().contains("\\")
                        || usuario.getSenha().contains("^")) {
                    erros.add("A senha contém caracteres especiais não suportados! Tente outra senha");
                }
            }
        }
        return erros;
    }

    public boolean validaLoginExistente(Usuario usuario) {
//        if (create) {
        em = JPAUtil.getInstance().getEntityManager();
        UsuarioDao dao = new UsuarioDao(em);
        Usuario u = dao.readByLogin(usuario.getLogin());
        em.close();

        if (u == null) {
            return true;
        } else {
            if (u.getId() == usuario.getId()) {
                return true;
            }
        }

        return false;
    }
}

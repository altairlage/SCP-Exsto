package scpexsto.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.Usuario;

public class UsuarioDao {

    private EntityManager em = null;

    public UsuarioDao(EntityManager em) {
        this.em = em;
    }

    public void create(Usuario usuario) {
        em.persist(usuario);
    }

    public Usuario readById(Long id) {
        return (em.find(Usuario.class, id));
    }

    public List<Usuario> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "nome", false));
    }

    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    public void Delete(Long id) {
        em.remove(this.readById(id));
    }

    public List<Usuario> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<Usuario> usuarios = null;

        String jpql = "select u from Usuario u where 1=1";

        //usuario
        if (criteria.containsKey("login")) {
            String login = (String) criteria.get("login");
            jpql += " and lower(u.login) like lower('%" + login.toString() + "%')";
        }

        //email
        if (criteria.containsKey("email")) {
            String email = (String) criteria.get("email");
            jpql += " and lower(u.email) like lower('%" + email.toString() + "%')";
        }

        //nome
        if (criteria.containsKey("nome")) {
            String nome = (String) criteria.get("nome");
            jpql += " and lower(u.nome) like lower('%" + nome.toString() + "%')";
        }

        //PERFIL
        if (criteria.containsKey("perfil")) {
            Integer perfil = (Integer) criteria.get("perfil");
            jpql += " and u.perfil = " + perfil.toString();
        }

        //ordenação
        jpql += " order by u." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        usuarios = query.getResultList();

        return usuarios;
    }

    public Usuario readByLogin(String login) {
        String jpql = "select u from Usuario u where 1=1"
                + " and lower(u.login) = lower('" + login.toString() + "')";
        Usuario u = null;
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);

        try {
            u = query.getSingleResult();
            System.out.println(u.getLogin());
        } catch (NoResultException ne) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }
}

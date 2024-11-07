package scpexsto.model.dao;

import com.oracle.jrockit.jfr.ContentType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.ClasseDefeitos;

public class ClasseDefeitosDao {

    private EntityManager em = null;

    public ClasseDefeitosDao(EntityManager em) {
        this.em = em;
    }

    public void create(ClasseDefeitos classe) {
        em.persist(classe);
    }

    public ClasseDefeitos readById(Long id) {
        return (em.find(ClasseDefeitos.class, id));
    }
    
    public ClasseDefeitos readByDescricao(String desc) {
        return (em.find(ClasseDefeitos.class, desc));
    }

    public void update(ClasseDefeitos classe) {
        this.em.merge(classe);
    }

    public void Delete(Long id) {
        em.remove(this.readById(id));
    }

    public List<ClasseDefeitos> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "id", true));
    }

    public List<ClasseDefeitos> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<ClasseDefeitos> classes = null;

        String jpql = "select c from ClasseDefeitos c where 1=1";

        //criteria
        if (criteria.containsKey("descricao")) {
            String desc = (String) criteria.get("descricao");

            jpql += " and lower(c.descricao) like lower('%" + desc + "%')";
        }

        if (criteria.containsKey("tipo")) {
            String tipo = (String) criteria.get("tipo");

            jpql += " and lower(c.tipo) like lower('%" + tipo + "%')";
        }

        //ordenação
        jpql += " order by c." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<ClasseDefeitos> query = em.createQuery(jpql, ClasseDefeitos.class);
        classes = query.getResultList();

        return classes;
    }
    
    public ClasseDefeitos readByDescWithQuery(String desc) {

        Query query = em.createQuery("select c from ClasseDefeitos c where c.descricao = :desc");
        query.setParameter("desc", desc);

        ClasseDefeitos c = null;

        try {
            c = (ClasseDefeitos) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return c;
    }
}

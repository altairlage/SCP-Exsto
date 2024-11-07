package scpexsto.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.ClasseSolucao;

public class ClasseSolucaoDao {

    private EntityManager em = null;

    public ClasseSolucaoDao(EntityManager em) {
        this.em = em;
    }

    public void create(ClasseSolucao classe) {
        em.persist(classe);
    }

    public ClasseSolucao readById(Long id) {
        return (em.find(ClasseSolucao.class, id));
    }
    
    public ClasseSolucao readByDescricao(String desc) {
        return (em.find(ClasseSolucao.class, desc));
    }

    public void update(ClasseSolucao classe) {
        this.em.merge(classe);
    }

    public void Delete(Long id) {
        em.remove(this.readById(id));
    }

    public List<ClasseSolucao> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "id", true));
    }

    public List<ClasseSolucao> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<ClasseSolucao> classes = null;

        String jpql = "select c from ClasseSolucao c where 1=1";

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

        TypedQuery<ClasseSolucao> query = em.createQuery(jpql, ClasseSolucao.class);
        classes = query.getResultList();

        return classes;
    }
    
    public ClasseSolucao readByDescWithQuery(String desc) {

        Query query = em.createQuery("select c from ClasseSolucao c where c.descricao = :desc");
        query.setParameter("desc", desc);

        ClasseSolucao c = null;

        try {
            c = (ClasseSolucao) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return c;
    }
}

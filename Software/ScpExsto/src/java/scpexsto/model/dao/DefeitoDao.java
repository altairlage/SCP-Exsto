package scpexsto.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.Defeito;

public class DefeitoDao {

    private EntityManager em = null;

    public DefeitoDao(EntityManager em) {
        this.em = em;
    }

    public void create(Defeito def) {
        em.persist(def);
    }

    public Defeito readById(Long id) {
        return em.find(Defeito.class, id);
    }

    public void update(Defeito def) {
        em.merge(def);
    }

    public void Delete(Long id) {
        em.remove(this.readById(id));
    }

    public List<Defeito> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "id", true));
    }

    public List<Defeito> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<Defeito> defeitos = null;

        String jpql = "select d from Defeito d where 1=1";

        //criteria
        //observacoes
        if (criteria.containsKey("observacoes")) {
            String observacoes = (String) criteria.get("observacoes");

            jpql += "and lower(d.observacoes) like lower('%" + observacoes + "%')";
        }

        //loc1
        if (criteria.containsKey("loc1")) {
            String loc1 = (String) criteria.get("loc1");

            jpql += "and lower(d.loc1) like lower('%" + loc1 + "%')";
        }

        //loc2
        if (criteria.containsKey("loc2")) {
            String loc2 = (String) criteria.get("loc2");

            jpql += "and lower(d.loc2) like lower('%" + loc2 + "%')";
        }

        //loc3
        if (criteria.containsKey("loc3")) {
            String loc3 = (String) criteria.get("loc3");

            jpql += "and lower(d.loc3) like lower('%" + loc3 + "%')";
        }

        //loc4
        if (criteria.containsKey("LOC4")) {
            String loc4 = (String) criteria.get("loc4");

            jpql += "and lower(d.loc4) like lower('%" + loc4 + "%')";
        }

        //loc5
        if (criteria.containsKey("loc5")) {
            String loc5 = (String) criteria.get("loc5");

            jpql += "and lower(d.loc5) like lower('%" + loc5 + "%')";
        }

        //ordenação
        jpql += " order by d." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<Defeito> query = em.createQuery(jpql, Defeito.class);
        defeitos = query.getResultList();

        return defeitos;
    }
}

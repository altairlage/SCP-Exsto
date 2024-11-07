package scpexsto.model.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.Retrabalho;

public class RetrabalhoDao {

    private EntityManager em = null;

    public RetrabalhoDao(EntityManager em) {
        this.em = em;
    }

    public void create(Retrabalho ret) {
        em.persist(ret);
    }

    public Retrabalho readById(Long id) {
        return em.find(Retrabalho.class, id);
    }

    public void update(Retrabalho ret) {
        em.merge(ret);
    }

    public void Delete(Long id) {
        em.remove(this.readById(id));
    }

    public List<Retrabalho> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "dataInicio", false));
    }

    public List<Retrabalho> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<Retrabalho> rets = null;

        String jpql = "select r from Retrabalho r where 1=1";

        //origem
        if (criteria.containsKey("origem")) {
            String origem = (String) criteria.get("origem");
            jpql += " and lower(r.origem) like lower('%" + origem + "%')";
        }

        //data de inicio
        if (criteria.containsKey("datainicio")) {
            Date datainicio = (Date) criteria.get("datainicio");

            Timestamp ts = new Timestamp(datainicio.getTime());
            jpql += " and r.dataInicio = '" + ts.toString() + "'";
        }

        //data de fim
        if (criteria.containsKey("datafim")) {
            Date datafim = (Date) criteria.get("datafim");

            Timestamp ts = new Timestamp(datafim.getTime());
            jpql += " and r.dataFim = '" + ts.toString() + "'";
        }

        //refugou
        if (criteria.containsKey("refugou")) {
            Boolean refugou = (Boolean) criteria.get("refugou");
            jpql += " and r.refugou = " + refugou.toString();
        }

        //ordenação
        jpql += " order by r." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<Retrabalho> query = em.createQuery(jpql, Retrabalho.class);
        rets = query.getResultList();

        return rets;
    }
}

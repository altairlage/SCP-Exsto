package scpexsto.model.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.TesteEletrico;

public class TesteEletricoDao {

    private EntityManager em = null;

    public TesteEletricoDao(EntityManager em) {
        this.em = em;
    }

    public void create(TesteEletrico teste) {
        em.persist(teste);
    }

    public TesteEletrico readById(Long id) {
        return em.find(TesteEletrico.class, id);
    }

    public void update(TesteEletrico teste) {
        em.merge(teste);
    }

    public void Delete(Long id) {
        em.remove(this.readById(id));
    }

    public List<TesteEletrico> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "dataInicio", false));
    }

    public List<TesteEletrico> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<TesteEletrico> testes = null;

        String jpql = "select t from TesteEletrico i where 1=1";

        //CRITERIA
        //data de inicio
        if (criteria.containsKey("datainicio")) {
            Date datainicio = (Date) criteria.get("datainicio");

            Timestamp ts = new Timestamp(datainicio.getTime());
            jpql += " and t.dataInicio = '" + ts.toString() + "'";
        }

        //data programada de fim
        if (criteria.containsKey("datafim")) {
            Date datafim = (Date) criteria.get("datafim");

            Timestamp ts = new Timestamp(datafim.getTime());
            jpql += " and t.dataFim = '" + ts.toString() + "'";
        }

        //voltagem
        if (criteria.containsKey("voltagem")) {
            String voltagem = (String) criteria.get("voltagem");
            jpql = " and lower(t.voltagem) like lower('%" + voltagem.toString() + "%')";
        }

        //ordenação
        jpql += " order by t." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<TesteEletrico> query = em.createQuery(jpql, TesteEletrico.class);
        testes = query.getResultList();

        return testes;
    }
}

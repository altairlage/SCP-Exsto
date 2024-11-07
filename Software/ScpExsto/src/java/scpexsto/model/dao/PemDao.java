package scpexsto.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.Pem;

public class PemDao {

    private EntityManager em = null;

    public PemDao(EntityManager em) {
        this.em = em;
    }

    public void create(Pem pem) {
        em.persist(pem);
    }

    public Pem readByNserie(String nserie) {
        return em.find(Pem.class, nserie);
    }

    public void update(Pem pem) {
        em.merge(pem);
    }

    public void Delete(String nserie) {
        em.remove(this.readByNserie(nserie));
    }

    public List<Pem> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "nserie", true));
    }

    public List<Pem> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<Pem> pems = null;

        String jpql = "select p  from Pem p where 1=1";

        if (criteria.containsKey("refugo")) {
            Boolean refugo = (Boolean) criteria.get("refugo");
            jpql += " and p.refugo = " + refugo.toString();
        }

        if (criteria.containsKey("motivorefugo")) {
            String motivo = (String) criteria.get("motivorefugo");
            jpql += " and lower(p.motivoRefugo) like lower('%" + motivo.toString() + "%')";
        }

        if (criteria.containsKey("etapa")) {
            String etapa = (String) criteria.get("etapa");
            jpql += " and lower(p.etapa) like lower('%" + etapa.toString() + "%')";
        }

        if (criteria.containsKey("status")) {
            Integer status = (Integer) criteria.get("status");
            jpql += " and p.status = " + status.toString();
        }

        if (criteria.containsKey("qtdtesteeletrico")) {
            Integer qtdteste = (Integer) criteria.get("qtdtesteeletrico");
            jpql += " and p.qtdTesteEletrico = " + qtdteste.toString();
        }

        //ordenação
        jpql += " order by p." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<Pem> query = em.createQuery(jpql, Pem.class);
        pems = query.getResultList();

        return pems;
    }
}

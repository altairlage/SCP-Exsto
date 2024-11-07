package scpexsto.model.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.IntegracaoFinal;

public class IntegracaoFinalDao {

    EntityManager em = null;

    public IntegracaoFinalDao(EntityManager em) {
        this.em = em;
    }

    public void create(IntegracaoFinal intfin) {
        em.persist(intfin);
    }

    public IntegracaoFinal readById(Long id) {
        return em.find(IntegracaoFinal.class, id);
    }

    public void update(IntegracaoFinal intfin) {
        em.merge(intfin);
    }

    public void Delete(Long id) {
        em.remove(this.readById(id));
    }

    public List<IntegracaoFinal> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "dataInicio", false));
    }

    public List<IntegracaoFinal> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<IntegracaoFinal> inspecoes = null;

        String jpql = "select i  from IntegracaoFinal i where 1=1";

        //CRITERIA
        //data de inicio
        if (criteria.containsKey("datainicio")) {
            Date datainicio = (Date) criteria.get("datainicio");

            Timestamp ts = new Timestamp(datainicio.getTime());
            jpql += " and i.dataInicio = '" + ts.toString() + "'";
        }

        //data de fim
        if (criteria.containsKey("datafim")) {
            Date datafim = (Date) criteria.get("datafim");

            Timestamp ts = new Timestamp(datafim.getTime());
            jpql += " and i.dataFim = '" + ts.toString() + "'";
        }

        //por intervalo de data de inicio
        if (criteria.containsKey("iniciointervaloini") && criteria.containsKey("fimintervaloini")) {
            Date datainicio = (Date) criteria.get("iniciointervaloini");
            Date datafim = (Date) criteria.get("fimintervaloini");

            Timestamp tsi = new Timestamp(datainicio.getTime());
            Timestamp tsf = new Timestamp(datafim.getTime());

            jpql += " and i.dataInicio between '" + tsi.toString() + "' and '" + tsf.toString() + "'";
        }

        //por intervalo de data de fim
        if (criteria.containsKey("iniciointervalofim") && criteria.containsKey("fimintervalofim")) {
            Date datainicio = (Date) criteria.get("iniciointervalofim");
            Date datafim = (Date) criteria.get("fimintervalofim");

            Timestamp tsi = new Timestamp(datainicio.getTime());
            Timestamp tsf = new Timestamp(datafim.getTime());

            jpql += " and i.dataFim between '" + tsi.toString() + "' and '" + tsf.toString() + "'";
        }

        //por quantidade de reprocesso
        if (criteria.containsKey("qtdreprocesso")) {
            Integer qtdrep = (Integer) criteria.get("qtdreprocesso");

            jpql += " and i.qtdReprocesso = " + qtdrep.toString();
        }

        //ordenação
        jpql += " order by i." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<IntegracaoFinal> query = em.createQuery(jpql, IntegracaoFinal.class);
        inspecoes = query.getResultList();

        return inspecoes;
    }
}

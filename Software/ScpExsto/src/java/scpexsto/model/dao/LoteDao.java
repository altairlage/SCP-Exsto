package scpexsto.model.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.Lote;

public class LoteDao {

    private EntityManager em = null;

    public LoteDao(EntityManager em) {
        this.em = em;
    }

    public void create(Lote lote) {
        em.persist(lote);
    }

    public Lote readById(String id) {
        return (em.find(Lote.class, id));
    }

    public void update(Lote lote) {
        em.merge(lote);
    }

    public void Delete(String id) {
        em.remove(this.readById(id));
    }

    public List<Lote> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "dataCriacao", false));
    }

    public List<Lote> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<Lote> lotes = null;

        String jpql = "select l  from Lote l where 1=1";

        //CRITERIA
        //data de criacao
        if (criteria.containsKey("dataCriacao")) {
            Date datainicio = (Date) criteria.get("dataCriacao");

            Timestamp ts = new Timestamp(datainicio.getTime());
            jpql += " and l.dataCriacao = '" + ts.toString() + "'";
        }

        //Nserie inicial
        if (criteria.containsKey("nserieinicial")) {
            Long nserie = (Long) criteria.get("nserieinicial");
            jpql += " and l.nserieInicial = " + nserie.toString();
        }

        //Nserie final
        if (criteria.containsKey("nseriefinal")) {
            Long nserie = (Long) criteria.get("nseriefinal");
            jpql += " and l.nserieFinal = " + nserie.toString();
        }

        //qtd
        if (criteria.containsKey("qtd")) {
            Integer qtd = (Integer) criteria.get("qtd");
            jpql += " and l.qtd = " + qtd.toString();
        }

        //data de producao
        if (criteria.containsKey("dataproducao")) {
            Date dataprod = (Date) criteria.get("dataproducao");

            Timestamp ts = new Timestamp(dataprod.getTime());
            jpql += " and l.dataProd = '" + ts.toString() + "'";
        }

        //data programada de inicio
        if (criteria.containsKey("dataProgInicio")) {
            Date dataprod = (Date) criteria.get("dataProgInicio");

            Timestamp ts = new Timestamp(dataprod.getTime());
            jpql += " and l.dataProgInicio = '" + ts.toString() + "'";
        }

        //data programada de fim
        if (criteria.containsKey("dataProgFim")) {
            Date dataprod = (Date) criteria.get("dataProgFim");

            Timestamp ts = new Timestamp(dataprod.getTime());
            jpql += " and l.dataProgFim = '" + ts.toString() + "'";
        }

        //Tipo de produto
        if (criteria.containsKey("tipoProduto")) {
            String tipo = (String) criteria.get("tipoProduto");

            jpql += " and l.tipoProduto = '" + tipo + "'";
        }
        
        //Status
        if (criteria.containsKey("status")) {
            String status = (String) criteria.get("status");

            jpql += " and l.status = '" + status + "'";
        }

        //ordenação
        jpql += " order by l." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<Lote> query = em.createQuery(jpql, Lote.class);
        lotes = query.getResultList();

        return lotes;
    }

    public Lote getLoteByIdWithJoinQueryProdutos(String id) {

        Query query = em.createQuery("select l from Lote l join fetch l.produtos where l.id = :id");
        query.setParameter("id", id);

        Lote l = null;

        try {
            l = (Lote) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return l;
    }
}

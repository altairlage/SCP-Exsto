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
import scpexsto.model.entity.Produto;

public class ProdutoDao {

    private EntityManager em = null;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void create(Produto produto) {
        em.persist(produto);
    }

    public Produto readByNserie(String nserie) {
        return em.find(Produto.class, nserie);
    }

    public void update(Produto produto) {
        em.merge(produto);
    }

    public void Delete(String nserie) {
        em.remove(this.readByNserie(nserie));
    }

    public List<Produto> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "nserie", true));
    }

    public List<Produto> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<Produto> produto = null;

        String jpql = "select p  from Produto p where 1=1";

        if (criteria.containsKey("revisao")) {
            String revisao = (String) criteria.get("revisao");
            jpql = " and lower(p.revisao) like lower('%" + revisao.toString() + "%')";
        }

        //data de producao
        if (criteria.containsKey("dataproducao")) {
            Date dataprod = (Date) criteria.get("dataproducao");

            Timestamp ts = new Timestamp(dataprod.getTime());
            jpql += " and i.dataProd = '" + ts.toString() + "'";
        }

        //refugo
        if (criteria.containsKey("refugo")) {
            Boolean refugo = (Boolean) criteria.get("refugo");
            jpql += " and p.refugo = " + refugo.toString();
        }

        //motivo refugo
        if (criteria.containsKey("motivorefugo")) {
            String motivo = (String) criteria.get("motivorefugo");
            jpql += " and lower(p.motivoRefugo) like lower('%" + motivo.toString() + "%')";
        }

        //etapa
        if (criteria.containsKey("etapa")) {
            String etapa = (String) criteria.get("etapa");
            jpql += " and lower(p.etapa) like lower('%" + etapa.toString() + "%')";
        }

        //status
        if (criteria.containsKey("status")) {
            Integer status = (Integer) criteria.get("status");
            jpql += " and p.status = " + status.toString();
        }

        //quantidade teste eletrico
        if (criteria.containsKey("qtdtesteeletrico")) {
            Integer qtdteste = (Integer) criteria.get("qtdtesteeletrico");
            jpql += " and p.qtdTesteEletrico = " + qtdteste.toString();
        }

        //ordenação
        jpql += " order by p." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
        produto = query.getResultList();

        return produto;
    }

    public Produto getProdutoByNSerieWithJoinQueryPems(String nserie) {
        Query query = em.createQuery("select p from Produto p join fetch p.pems where p.nserie = :nserie");
        query.setParameter("nserie", nserie);

        Produto p = null;

        try {
            p = (Produto) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return p;
    }
}

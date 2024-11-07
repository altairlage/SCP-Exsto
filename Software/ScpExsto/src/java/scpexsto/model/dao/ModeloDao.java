package scpexsto.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import scpexsto.model.entity.Lote;
import scpexsto.model.entity.Modelo;
import scpexsto.model.entity.Produto;

public class ModeloDao {

    private EntityManager em = null;

    public ModeloDao(EntityManager em) {
        this.em = em;
    }

    public void create(Modelo modelo) {
        em.persist(modelo);
    }

    public Modelo readById(Long id) {
        return em.find(Modelo.class, id);
    }

    public void update(Modelo modelo) {
        em.merge(modelo);
    }

    public void Delete(Long id) {
        em.remove(this.readById(id));
    }

    public List<Modelo> readAll() {
        return (readByCriteria(new HashMap<String, Object>(), "modelo", true));
    }

    public List<Modelo> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        List<Modelo> modelos = null;

        String jpql = "select m  from Modelo m where 1=1";

        //CRITERIA
        //modelo
        if (criteria.containsKey("modelo")) {
            String modelo = (String) criteria.get("modelo");
            jpql += " and lower(m.modelo) like lower('%" + modelo + "')";
        }

        //descricao
        if (criteria.containsKey("descricao")) {
            String desc = (String) criteria.get("descricao");
            jpql += " and lower(m.descricao) like lower('%" + desc + "')";
        }

        //tipo
        if (criteria.containsKey("tipo")) {
            String tipo = (String) criteria.get("tipo");
            jpql += " and lower(m.tipo) like lower('%" + tipo + "')";
        }

        //ordenação
        jpql += " order by m." + orderField;
        if (!asc) {
            jpql += " desc";
        }

        TypedQuery<Modelo> query = em.createQuery(jpql, Modelo.class);
        modelos = query.getResultList();

        return modelos;
    }

    public Modelo getModeloByLoteWithQuery(String id) {

        Query query = em.createQuery("select l from Lote l join fetch l.modelo where l.id = :id");
        query.setParameter("id", id);

        Lote l = null;
        Modelo m = null;

        try {
            l = (Lote) query.getSingleResult();
            if (l != null) {
                m = l.getModelo();
            }
        } catch (NoResultException e) {
            return null;
        }

        return m;
    }

//    public Modelo getModeloByProdutoWithQuery(String nserie) {
//        Query query = em.createQuery("select p from Produto p join fetch p.modelo where p.nserie = :nserie");
//        query.setParameter("nserie", nserie);
//
//        Produto p = null;
//        Modelo m = null;
//
//        try {
//            p = (Produto) query.getSingleResult();
//            if (p != null) {
//                m = p.getModelo();
//            }
//        } catch (NoResultException e) {
//            return null;
//        }
//
//        return m;
//    }
}

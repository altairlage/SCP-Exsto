package scpexsto.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import scpexsto.model.BaseService;
import scpexsto.model.JPAUtil;
import scpexsto.model.dao.ModeloDao;
import scpexsto.model.entity.Modelo;

public class ModeloService implements BaseService<Modelo> {

    private EntityManager em;

    @Override
    public void create(Modelo m) {
        em = JPAUtil.getInstance().getEntityManager();
        ModeloDao dao = new ModeloDao(em);
        em.getTransaction().begin();

        dao.create(m);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Modelo m) {
        em = JPAUtil.getInstance().getEntityManager();
        ModeloDao dao = new ModeloDao(em);
        em.getTransaction().begin();

        dao.update(m);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Modelo> readAll() {
        em = JPAUtil.getInstance().getEntityManager();
        ModeloDao dao = new ModeloDao(em);

        List<Modelo> modelos = dao.readByCriteria(new HashMap<String, Object>(), "id", false);
        em.close();

        return modelos;
    }

    @Override
    public List<Modelo> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        em = JPAUtil.getInstance().getEntityManager();
        ModeloDao dao = new ModeloDao(em);
        List<Modelo> modelos = dao.readByCriteria(criteria, orderField, asc);
        em.close();

        return modelos;
    }

    public void delete(Long id) {
        em = JPAUtil.getInstance().getEntityManager();
        ModeloDao dao = new ModeloDao(em);
        em.getTransaction().begin();

        dao.Delete(id);

        em.getTransaction().commit();
        em.close();
    }

    public Modelo readById(Long id) {
        em = JPAUtil.getInstance().getEntityManager();
        ModeloDao dao = new ModeloDao(em);
        Modelo m = dao.readById(id);
        em.close();

        return m;
    }

    public List<String> validaCampos(Modelo m) {
        List<String> erros = new ArrayList<String>();

        if (m.getModelo() == null || m.getModelo().equals("") || m.getModelo().equals(" ")) {
            erros.add("O modelo é obrigatório!");
        }
        if (m.getTipo() == null || m.getTipo().equals("") || m.getTipo().equals(" ")) {
            erros.add("O tipo de produto é obrigatório!");
        }
        return erros;
    }

    public boolean validaModeloExistente(Modelo m) {

        em = JPAUtil.getInstance().getEntityManager();
        ModeloDao dao = new ModeloDao(em);
        Modelo other = this.readByModelo(m.getModelo());
        em.close();

        if (other == null) {
            return true;
        } else {
            if (other.getId() == m.getId()) {
                return true;
            }
        }
        return false;
    }

    public Modelo readByModelo(String modelo) {
        em = JPAUtil.getInstance().getEntityManager();
            
        String jpql = "select m from Modelo m where 1=1"
                + " and lower(m.modelo) = lower('" + modelo + "')";
        Modelo m = null;
        TypedQuery<Modelo> query = em.createQuery(jpql, Modelo.class);

        try {
            m = query.getSingleResult();
            System.out.println(m.getModelo());
        } catch (NoResultException ne) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    public List<String> getModelosAsString() {

        List<String> modelos = new ArrayList<String>();

        em = JPAUtil.getInstance().getEntityManager();
        ModeloDao dao = new ModeloDao(em);

        List<Modelo> lista = dao.readByCriteria(new HashMap<String, Object>(), "id", false);
        em.close();

        if (lista == null || lista.size() <= 0) {
            modelos = null;
        } else {
            for (Iterator<Modelo> it = lista.iterator(); it.hasNext();) {
                modelos.add(it.next().getModelo());
            }
        }
        return modelos;
    }
    
    public Modelo getModeloByLoteWithQuery(String id) {
        em = JPAUtil.getInstance().getEntityManager();

        ModeloDao dao = new ModeloDao(em);

        Modelo m = dao.getModeloByLoteWithQuery(id);

        em.close();

        return m;
    }

//    public Modelo getModeloByProdutoWithQuery(String nserie) {
//        em = JPAUtil.getInstance().getEntityManager();
//
//        ModeloDao dao = new ModeloDao(em);
//
//        Modelo m = dao.getModeloByProdutoWithQuery(nserie);
//
//        em.close();
//
//        return m;
//    }
}

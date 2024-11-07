package scpexsto.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import scpexsto.model.BaseService;
import scpexsto.model.JPAUtil;
import scpexsto.model.dao.PemDao;
import scpexsto.model.entity.Pem;

public class PemService implements BaseService<Pem> {

    private EntityManager em;

    @Override
    public void create(Pem p) {
        em = JPAUtil.getInstance().getEntityManager();
        PemDao dao = new PemDao(em);
        em.getTransaction().begin();

        dao.create(p);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Pem p) {
        em = JPAUtil.getInstance().getEntityManager();
        PemDao dao = new PemDao(em);
        em.getTransaction().begin();

        dao.update(p);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Pem> readAll() {
        em = JPAUtil.getInstance().getEntityManager();
        PemDao dao = new PemDao(em);

        List<Pem> pems = dao.readByCriteria(new HashMap<String, Object>(), "nserie", false);
        em.close();

        return pems;
    }

    @Override
    public List<Pem> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        em = JPAUtil.getInstance().getEntityManager();
        PemDao dao = new PemDao(em);
        List<Pem> pems = dao.readByCriteria(criteria, orderField, asc);
        em.close();

        return pems;
    }

    public void delete(String nserie) {
        em = JPAUtil.getInstance().getEntityManager();
        PemDao dao = new PemDao(em);
        em.getTransaction().begin();

        dao.Delete(nserie);

        em.getTransaction().commit();
        em.close();
    }

    public Pem readById(String nserie) {
        em = JPAUtil.getInstance().getEntityManager();
        PemDao dao = new PemDao(em);

        Pem p = dao.readByNserie(nserie);
        em.close();

        return p;
    }

    public List<String> validaCampos(Pem p) {
        List<String> erros = new ArrayList<String>();

        if (p.getNserie() == null || p.getNserie().equals("") || p.getNserie().equals(" ")) {
            erros.add("O Número de série é obrigatório!");
        }

        return erros;
    }

    public boolean validaPemExistente(Pem p) {
        em = JPAUtil.getInstance().getEntityManager();
        PemDao dao = new PemDao(em);
        Pem other = dao.readByNserie(p.getNserie());
        em.close();

        if (other == null) {
            return true;
        } else {
            if (other.getNserie().equals(p.getNserie())) {
                return true;
            }
        }   
        return false;
    }

}

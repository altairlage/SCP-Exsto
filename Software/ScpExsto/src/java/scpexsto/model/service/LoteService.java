package scpexsto.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import scpexsto.model.BaseService;
import scpexsto.model.JPAUtil;
import scpexsto.model.dao.LoteDao;
import scpexsto.model.entity.Lote;

public class LoteService implements BaseService<Lote> {

    private EntityManager em;

    @Override
    public void create(Lote l) {
        em = JPAUtil.getInstance().getEntityManager();
        LoteDao dao = new LoteDao(em);
        em.getTransaction().begin();

        dao.create(l);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Lote l) {
        em = JPAUtil.getInstance().getEntityManager();
        LoteDao dao = new LoteDao(em);
        em.getTransaction().begin();

        dao.update(l);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Lote> readAll() {
        em = JPAUtil.getInstance().getEntityManager();
        LoteDao dao = new LoteDao(em);

        List<Lote> lotes = dao.readByCriteria(new HashMap<String, Object>(), "id", false);
        em.close();

        return lotes;
    }

    @Override
    public List<Lote> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        em = JPAUtil.getInstance().getEntityManager();
        LoteDao dao = new LoteDao(em);
        List<Lote> lotes = dao.readByCriteria(criteria, orderField, asc);
        em.close();

        return lotes;
    }

    public void delete(String id) {
        em = JPAUtil.getInstance().getEntityManager();
        LoteDao dao = new LoteDao(em);
        em.getTransaction().begin();

        dao.Delete(id);

        em.getTransaction().commit();
        em.close();
    }

    public Lote readById(String id) {
        em = JPAUtil.getInstance().getEntityManager();
        LoteDao dao = new LoteDao(em);

        Lote l = dao.readById(id);
        em.close();

        return l;
    }

    public List<String> validaCampos(Lote l) {
        List<String> erros = new ArrayList<String>();

        if (l.getDataCriacao() == null) {
            erros.add("A data de criação é obrigatória!");
        }

        if (l.getQtd() <= 0) {
            erros.add("A quantidade é obrigatória e deve ser maior de 0.");
        }

        if (l.getId().equals("") || l.getId().equals(" ") || l.getId() == null) {
            erros.add("O ID do lote é obrigatório!");
        }

        if (l.getDataProgInicio() == null) {
            erros.add("A data de inicio programado é obrigatória!");
        }

        if (l.getDataProgFim() == null) {
            erros.add("A data de fim programado é obrigatória!");
        }

        if (l.getModelo() == null) {
            erros.add("O modelo informado não existe");
        }

        return erros;
    }

    public boolean validaLoteExistente(Lote l) {

        em = JPAUtil.getInstance().getEntityManager();
        LoteDao dao = new LoteDao(em);
        Lote other = dao.readById(l.getId());
        em.close();

        if (other == null) {
            return true;
        } else {
            if (other.getId().equals(l.getId()) && other.getDataCriacao().equals(l.getDataCriacao())) {
                return true;
            }
        }

        return false;
    }

    public Lote getLoteByIdWithJoinQueryProdutos(String id) {
        em = JPAUtil.getInstance().getEntityManager();

        LoteDao dao = new LoteDao(em);

        Lote l = dao.getLoteByIdWithJoinQueryProdutos(id);

        em.close();

        return l;
    }
    
    public List<String> getLotesAsString(){
        List<String> lotes = new ArrayList<String>();

        em = JPAUtil.getInstance().getEntityManager();
        LoteDao dao = new LoteDao(em);

        List<Lote> lista = dao.readByCriteria(new HashMap<String, Object>(), "id", false);
        em.close();

        if (lista == null || lista.size() <= 0) {
            lotes = null;
        } else {
            for (Iterator<Lote> it = lista.iterator(); it.hasNext();) {
                lotes.add(it.next().getId());
            }
        }
        return lotes;
    }
}

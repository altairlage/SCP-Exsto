package scpexsto.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import scpexsto.model.BaseService;
import scpexsto.model.JPAUtil;
import scpexsto.model.dao.ProdutoDao;
import scpexsto.model.entity.Produto;

public class ProdutoService implements BaseService<Produto> {

    private EntityManager em;

    @Override
    public void create(Produto p) {
        em = JPAUtil.getInstance().getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);
        em.getTransaction().begin();

        dao.create(p);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(Produto p) {
        em = JPAUtil.getInstance().getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);
        em.getTransaction().begin();

        dao.update(p);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Produto> readAll() {
        em = JPAUtil.getInstance().getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);

        List<Produto> produtos = dao.readByCriteria(new HashMap<String, Object>(), "nserie", false);
        em.close();

        return produtos;
    }

    @Override
    public List<Produto> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        em = JPAUtil.getInstance().getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);
        List<Produto> produtos = dao.readByCriteria(criteria, orderField, asc);
        em.close();

        return produtos;
    }

    public void delete(String nserie) {
        em = JPAUtil.getInstance().getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);
        em.getTransaction().begin();

        dao.Delete(nserie);

        em.getTransaction().commit();
        em.close();
    }

    public Produto readById(String nserie) {
        em = JPAUtil.getInstance().getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);

        Produto p = dao.readByNserie(nserie);
        em.close();

        return p;
    }

    public List<String> validaCampos(Produto p) {
        List<String> erros = new ArrayList<String>();

        if (p.getNserie() == null || p.getNserie().equals("") || p.getNserie().equals(" ")) {
            erros.add("O Número de série é obrigatório!");
        }

        return erros;
    }

    public boolean validaProdutoExistente(Produto p) {
        em = JPAUtil.getInstance().getEntityManager();
        ProdutoDao dao = new ProdutoDao(em);
        Produto other = dao.readByNserie(p.getNserie());
        em.close();

        if (other == null) {
            return true;
        } else {
            if (other.getNserie().equals(p.getNserie()) && other.getDataCriacao().equals(p.getDataCriacao())) {
                return true;
            }
        }
        return false;
    }

    public Produto getProdutoByNSerieWithJoinQueryPems(String nserie) {
         em = JPAUtil.getInstance().getEntityManager();

        ProdutoDao dao = new ProdutoDao(em);

        Produto p = dao.getProdutoByNSerieWithJoinQueryPems(nserie);

        em.close();

        return p;
    }
}

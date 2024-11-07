package scpexsto.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import scpexsto.model.BaseService;
import scpexsto.model.JPAUtil;
import scpexsto.model.dao.ClasseDefeitosDao;
import scpexsto.model.dao.ClasseSolucaoDao;
import scpexsto.model.entity.ClasseSolucao;

public class ClasseSolucaoService implements BaseService<ClasseSolucao> {

    private EntityManager em;

    @Override
    public void create(ClasseSolucao c) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseSolucaoDao dao = new ClasseSolucaoDao(em);

        em.getTransaction().begin();
        dao.create(c);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(ClasseSolucao c) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseSolucaoDao dao = new ClasseSolucaoDao(em);

        em.getTransaction().begin();
        dao.update(c);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<ClasseSolucao> readAll() {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseSolucaoDao dao = new ClasseSolucaoDao(em);
        List<ClasseSolucao> classes = dao.readByCriteria(new HashMap<String, Object>(), "id", false);
        em.close();

        return classes;
    }

    @Override
    public List<ClasseSolucao> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseSolucaoDao dao = new ClasseSolucaoDao(em);
        List<ClasseSolucao> classes = dao.readByCriteria(criteria, orderField, asc);
        em.close();

        //verificar se precisa abrir e dar commit na transação
        return classes;
    }

    public void delete(Long id) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseSolucaoDao dao = new ClasseSolucaoDao(em);
        em.getTransaction().begin();
        dao.Delete(id);
        em.getTransaction().commit();
        em.close();
    }

    public ClasseSolucao readById(Long id) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseSolucaoDao dao = new ClasseSolucaoDao(em);

        ClasseSolucao c = dao.readById(id);

        em.close();

        return c;
    }

    public List<String> validaCampos(ClasseSolucao c) {
        List<String> erros = new ArrayList<String>();

        if (c.getDescricao().equals("") || c.getDescricao().equals(" ") || c.getDescricao() == null) {
            erros.add("Descrição vazia ou inválida!");
        }

        if (c.getTipo().equals("") || c.getTipo().equals(" ") || c.getTipo() == null) {
            erros.add("Tipo vazio ou inválido!");
        }
        return erros;
    }

    public boolean validaClasseExistente(ClasseSolucao c) {
//        if (create) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseSolucaoDao dao = new ClasseSolucaoDao(em);
        ClasseSolucao classe = dao.readByDescWithQuery(c.getDescricao());
        em.close();

        if (classe == null) {
            return true;
        } else {
            if (classe.getId() == c.getId()) {
                return true;
            }
        }

        return false;
    }

}

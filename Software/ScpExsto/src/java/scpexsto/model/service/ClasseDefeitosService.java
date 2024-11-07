package scpexsto.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import scpexsto.model.BaseService;
import scpexsto.model.JPAUtil;
import scpexsto.model.dao.ClasseDefeitosDao;
import scpexsto.model.entity.ClasseDefeitos;

public class ClasseDefeitosService implements BaseService<ClasseDefeitos> {

    private EntityManager em;

    @Override
    public void create(ClasseDefeitos c) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseDefeitosDao dao = new ClasseDefeitosDao(em);

        em.getTransaction().begin();
        dao.create(c);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(ClasseDefeitos e) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseDefeitosDao dao = new ClasseDefeitosDao(em);

        em.getTransaction().begin();
        dao.update(e);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<ClasseDefeitos> readAll() {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseDefeitosDao dao = new ClasseDefeitosDao(em);
        List<ClasseDefeitos> classes = dao.readByCriteria(new HashMap<String, Object>(), "id", false);
        em.close();

        return classes;
    }

    @Override
    public List<ClasseDefeitos> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseDefeitosDao dao = new ClasseDefeitosDao(em);
        List<ClasseDefeitos> classes = dao.readByCriteria(criteria, orderField, asc);
        em.close();

        //verificar se precisa abrir e dar commit na transação
        return classes;
    }

    public void delete(Long id) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseDefeitosDao dao = new ClasseDefeitosDao(em);
        em.getTransaction().begin();
        dao.Delete(id);
        em.getTransaction().commit();
        em.close();
    }

    public ClasseDefeitos readById(Long id) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseDefeitosDao dao = new ClasseDefeitosDao(em);

        ClasseDefeitos c = dao.readById(id);

        em.close();

        return c;
    }

    public List<String> validaCampos(ClasseDefeitos c) {
        List<String> erros = new ArrayList<String>();

        if (c.getDescricao().equals("") || c.getDescricao().equals(" ") || c.getDescricao() == null) {
            erros.add("Descrição vazia ou inválida!");
        }

        if (c.getTipo().equals("") || c.getTipo().equals(" ") || c.getTipo() == null) {
            erros.add("Tipo vazio ou inválido!");
        }
        return erros;
    }

    public boolean validaClasseExistente(ClasseDefeitos c) {
//        if (create) {
        em = JPAUtil.getInstance().getEntityManager();
        ClasseDefeitosDao dao = new ClasseDefeitosDao(em);
        ClasseDefeitos equip = dao.readByDescWithQuery(c.getDescricao());
        em.close();

        if (equip == null) {
            return true;
        } else {
            if (equip.getId() == c.getId()) {
                return true;
            }
        }

        return false;
    }

}

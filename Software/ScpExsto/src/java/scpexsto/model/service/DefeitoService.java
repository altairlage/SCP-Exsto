package scpexsto.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import scpexsto.model.BaseService;
import scpexsto.model.JPAUtil;
import scpexsto.model.dao.DefeitoDao;
import scpexsto.model.entity.Defeito;

public class DefeitoService implements BaseService<Defeito>{
    
    EntityManager em;
    
    @Override
    public void create(Defeito defeito) {
        em = JPAUtil.getInstance().getEntityManager();
        DefeitoDao dao = new DefeitoDao(em);
        
        em.getTransaction().begin();
        dao.create(defeito);
        em.getTransaction().commit();
        
        em.close();
    }

    @Override
    public void update(Defeito defeito) {
        em = JPAUtil.getInstance().getEntityManager();
        DefeitoDao dao = new DefeitoDao(em);
        
        em.getTransaction().begin();
        dao.update(defeito);
        em.getTransaction().commit();
        
        em.close();
    }

    @Override
    public List<Defeito> readAll() {
        em = JPAUtil.getInstance().getEntityManager();
        DefeitoDao dao = new DefeitoDao(em);
        em.getTransaction().begin();
        List<Defeito> defeitos = dao.readByCriteria(new HashMap<String, Object>(), "id", false);
        em.close();
        
        //verificar se precisa abrir e dar commit na transação
        
        return defeitos;
    }

    @Override
    public List<Defeito> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        em = JPAUtil.getInstance().getEntityManager();
        DefeitoDao dao = new DefeitoDao(em);
        List<Defeito> defeitos = dao.readByCriteria(criteria, orderField, asc);
        em.close();

        //verificar se precisa abrir e dar commit na transação
        return defeitos;
    }
    
    public void delete(Long id){
        em = JPAUtil.getInstance().getEntityManager();
        DefeitoDao dao = new DefeitoDao(em);
        em.getTransaction().begin();
        dao.Delete(id);
        em.getTransaction().commit();
        em.close();
    }
    
    public Defeito readById(Long Id) {
        em = JPAUtil.getInstance().getEntityManager();
        DefeitoDao dao = new DefeitoDao(em);

        Defeito defeito = dao.readById(Id);

        return defeito;
    }
    
     public List<String> validaCampos(Defeito d) {
        List<String> erros = new ArrayList<String>();

        if (d.getLoc1() == null || d.getLoc1().equals("") || d.getLoc1().equals(" ")) {
            erros.add("Pelo menos a localização 1 deve ser preenchida!");
        }
        
        if (d.getClasseDefeito() == null) {
            erros.add("Classe vazia ou inválida!");
        }

        return erros;
    }
}

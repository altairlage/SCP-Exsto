package scpexsto.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import scpexsto.model.BaseService;
import scpexsto.model.JPAUtil;
import scpexsto.model.dao.EquipamentoMedicaoDao;
import scpexsto.model.entity.EquipamentoMedicao;

public class EquipamentoMedicaoService implements BaseService<EquipamentoMedicao> {

    private EntityManager em;

    @Override
    public void create(EquipamentoMedicao e) {
        em = JPAUtil.getInstance().getEntityManager();
        EquipamentoMedicaoDao dao = new EquipamentoMedicaoDao(em);

        em.getTransaction().begin();
        dao.create(e);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(EquipamentoMedicao e) {
        em = JPAUtil.getInstance().getEntityManager();
        EquipamentoMedicaoDao dao = new EquipamentoMedicaoDao(em);

        em.getTransaction().begin();
        dao.update(e);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<EquipamentoMedicao> readAll() {
        em = JPAUtil.getInstance().getEntityManager();
        EquipamentoMedicaoDao dao = new EquipamentoMedicaoDao(em);
        List<EquipamentoMedicao> usuarios = dao.readByCriteria(new HashMap<String, Object>(), "patrimonio", false);
        em.close();

        return usuarios;
    }

    @Override
    public List<EquipamentoMedicao> readByCriteria(Map<String, Object> criteria, String orderField, boolean asc) {
        em = JPAUtil.getInstance().getEntityManager();
        EquipamentoMedicaoDao dao = new EquipamentoMedicaoDao(em);
        List<EquipamentoMedicao> equips = dao.readByCriteria(criteria, orderField, asc);
        em.close();

        //verificar se precisa abrir e dar commit na transação
        return equips;
    }

    public void delete(String patrimonio) {
        em = JPAUtil.getInstance().getEntityManager();
        EquipamentoMedicaoDao dao = new EquipamentoMedicaoDao(em);
        em.getTransaction().begin();
        dao.Delete(patrimonio);
        em.getTransaction().commit();
        em.close();
    }

    public EquipamentoMedicao readByPatrimonio(String patrimonio) {
        em = JPAUtil.getInstance().getEntityManager();
        EquipamentoMedicaoDao dao = new EquipamentoMedicaoDao(em);

        EquipamentoMedicao e = dao.readByPatrimonio(patrimonio);

        em.close();

        return e;
    }

    public List<String> validaCampos(EquipamentoMedicao e) {
        List<String> erros = new ArrayList<String>();

        if (e.getPatrimonio().equals("") || e.getPatrimonio().equals(" ") || e.getPatrimonio() == null) {
            erros.add("Patrimônio vazio ou inválido!");
        }

        if (e.getDataVencimento() == null) {
            erros.add("Data de vencimento vazia ou inválida");
        }

//        Date agora = new Date(System.currentTimeMillis());
//        if (e.getDataVencimento().before(agora)) {
//            erros.add("Data vazia ou inválida");
//        }
        if (e.getTipo().equals("") || e.getTipo().equals(" ") || e.getTipo() == null) {
            erros.add("Tipo vazio ou inválido");
        }
        return erros;
    }

    public boolean validaEquipExistente(EquipamentoMedicao e) {
//        if (create) {
        em = JPAUtil.getInstance().getEntityManager();
        EquipamentoMedicaoDao dao = new EquipamentoMedicaoDao(em);
        EquipamentoMedicao equip = dao.readByPatrimonio(e.getPatrimonio());
        em.close();

        if (equip == null) {
            return true;
        } else {
            if (equip.getDataCriacao().equals(e.getDataCriacao())) {
                return true;
            }
        }

        return false;
    }

}
